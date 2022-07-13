package com.gimmenow.deliverynowrestapi.service;

import com.gimmenow.deliverynowrestapi.dto.User;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

@Service
public class UserService {

    @Value("${aws.user-pool.id}")
    private String POOL_ID;
    
    private static CognitoIdentityProviderClient cognitoClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public ListUsersResponse listUsers() {
        final ListUsersRequest listUsersRequest = ListUsersRequest.builder()
                .userPoolId(POOL_ID)
                .build();

        ListUsersResponse result = getCognitoClient().listUsers(listUsersRequest);
        return result;
    }

    public boolean isUserExist(String username) {
        if (listUsers().hasUsers()) {
            boolean userExists = listUsers().users().stream().anyMatch(user -> user.username().equals(username));
            return userExists;
        }
        return false;
    }

    public boolean createUser(User user) {
        if (!isUserExist(user.getUsername())) {

            try {
                List<AttributeType> userAttrs = new ArrayList();

                //flexible attributes
                AttributeType emailAttribute = AttributeType.builder()
                        .name("email")
                        .value(user.getEmail())
                        .build();

                AttributeType genderAttribute = AttributeType.builder()
                        .name("gender")
                        .value(user.getGender())
                        .build();

                userAttrs.add(emailAttribute);
                userAttrs.add(genderAttribute);

                AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                        .userPoolId(POOL_ID)
                        .username(user.getUsername())
                        .temporaryPassword(user.getPassword())
                        .userAttributes(userAttrs)
                        .messageAction("SUPPRESS")
                        .build();

                AdminCreateUserResponse response = getCognitoClient().adminCreateUser(userRequest);

                LOGGER.info(String.format("%s", response));
//                System.out.println("User " + response.user().username() + "is created. Status: " + response.user().userStatus());

                return true;
            } catch (CognitoIdentityProviderException e) {
                System.err.println(e.awsErrorDetails().errorMessage());
                System.exit(1);
            }

        }

        return false;
    }

    public static CognitoIdentityProviderClient getCognitoClient() {
        if (cognitoClient == null) {
            cognitoClient = CognitoIdentityProviderClient.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();
        }
        return cognitoClient;
    }

}
