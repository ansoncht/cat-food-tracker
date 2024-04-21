package com.ansoncht.catfoodtracker.user;

import com.ansoncht.catfoodtracker.proto.CreateUserReply;
import com.ansoncht.catfoodtracker.proto.CreateUserRequest;
import com.ansoncht.catfoodtracker.proto.TrackerGrpc;

import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcUserController extends TrackerGrpc.TrackerImplBase {
    private final UserService userService;

    public GrpcUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser(
            CreateUserRequest createUserRequest, StreamObserver<CreateUserReply> responseObserver) {
        System.out.println("Calling gRPC createUser()");
        System.out.println("Creating user with " + createUserRequest.getUsername());

        UserDTO.SignUpRequest signUpRequest = createUserDTOSignUpRequest(createUserRequest);

        UserDTO.SignUpResponse signUpResponse = this.userService.registerUser(signUpRequest);

        System.out.println("user creation successful with: " + signUpRequest.getUsername());

        CreateUserReply response = CreateUserReply.newBuilder().setUserId(signUpResponse.getId()).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public UserDTO.SignUpRequest createUserDTOSignUpRequest(CreateUserRequest createUserRequest) {
        return new UserDTO.SignUpRequest(
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getUsername(),
                createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getConfirmedPassword());
    }
}
