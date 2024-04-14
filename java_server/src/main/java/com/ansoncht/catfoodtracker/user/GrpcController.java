package com.ansoncht.catfoodtracker.user;

import com.ansoncht.catfoodtracker.proto.CreateUserReply;
import com.ansoncht.catfoodtracker.proto.CreateUserRequest;
import com.ansoncht.catfoodtracker.proto.TrackerGrpc;
import com.ansoncht.catfoodtracker.user.UserService;
import io.grpc.stub.StreamObserver;
import jakarta.validation.constraints.AssertTrue;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcController extends TrackerGrpc.TrackerImplBase {
    private final UserService userService;

    public GrpcController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest, StreamObserver<CreateUserReply> responseObserver) {
        if (!isEqual(createUserRequest.getPassword(), createUserRequest.getPassword())) {
            System.err.println("Passwords do not match");
            return;
        }

        System.out.println("Calling gRPC createUser()");
        System.out.println("Creating user with " + createUserRequest.getUsername());

        CreateUserReply response = CreateUserReply.newBuilder()
                .setUserId("asdasdsad")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @AssertTrue
    private boolean isEqual(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }
}
