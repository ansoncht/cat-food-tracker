package com.ansoncht.catfoodtracker.user;

import com.ansoncht.catfoodtracker.proto.*;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class GrpcUserController extends TrackerGrpc.TrackerImplBase {
    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(GrpcUserController.class);

    public GrpcUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser(
            CreateUserRequest createUserRequest, StreamObserver<CreateUserReply> responseObserver) {
        try {
            LOGGER.info("CreateUser() rpc is invoked");
            LOGGER.info(
                    String.format(
                            "user creation with email: %s; username: %s",
                            createUserRequest.getEmail(), createUserRequest.getUsername()));

            UserDTO.SignUpRequest signUpRequest =
                    UserDTO.createUserDTOSignUpRequest(createUserRequest);

            UserDTO.SignUpResponse signUpResponse = this.userService.registerUser(signUpRequest);
            if (signUpResponse != null) {
                LOGGER.info(
                        "\u001B[32m"
                                + "user creation succeeded with: "
                                + signUpRequest.getUsername()
                                + "\u001B[0m");

                CreateUserReply response =
                        CreateUserReply.newBuilder().setUserId(signUpResponse.getId()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                LOGGER.error("user creation failed for: " + signUpRequest.getUsername());

                throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.error("exception in CreateUser(): " + e.getMessage());
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void loginUser(
            LoginUserRequest loginUserRequest, StreamObserver<LoginUserReply> responseObserver) {
        try {
            LOGGER.info("LoginUser() rpc is invoked");
            LOGGER.info(
                    String.format(
                            "user login with email: %s; username: %s",
                            loginUserRequest.getEmail(), loginUserRequest.getUsername()));

            UserDTO.SignInRequest signInRequest =
                    UserDTO.createUserDTOSignInRequest(loginUserRequest);

            UserDTO.SignInResponse signInResponse = this.userService.loginUser(signInRequest);
            if (signInResponse != null) {
                LOGGER.info(
                        "\u001B[32m"
                                + "user login succeeded with: "
                                + signInResponse.getUsername()
                                + "\u001B[0m");

                LoginUserReply response =
                        LoginUserReply.newBuilder().setUserId(signInResponse.getId()).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                LOGGER.error("user login failed for: " + loginUserRequest.getEmail());

                throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.error("exception in LoginUser(): " + e.getMessage());
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }
}
