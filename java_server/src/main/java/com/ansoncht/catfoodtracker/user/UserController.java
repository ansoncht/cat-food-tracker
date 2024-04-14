package com.ansoncht.catfoodtracker.user;

import com.ansoncht.catfoodtracker.proto.CreateUserReply;
import com.ansoncht.catfoodtracker.proto.CreateUserRequest;
import io.grpc.stub.StreamObserver;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final GrpcController grpcController;

    public UserController(GrpcController grpcController) {
        this.grpcController = grpcController;
    }

    @PostMapping("/user/signup")
    public String signUp(@Valid @RequestBody UserDTO.SignUpRequest signUpRequest) {
        System.out.println("Calling REST signUp()");

        final String[] result = new String[1];

        StreamObserver<CreateUserReply> responseObserver = new StreamObserver<CreateUserReply>() {
            @Override
            public void onNext(CreateUserReply value) {
                System.out.println("User created with ID: " + value.getUserId());
                result[0] = value.getUserId();
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error creating user: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("User creation completed");
            }
        };

        CreateUserRequest createUserRequest = signUpRequest.convertToCreateUserRequest();

        this.grpcController.createUser(createUserRequest, responseObserver);

        return result[0];
    }

    @PostMapping("/user/signin")
    public String signIn(@Valid @RequestBody UserDTO.SignInRequest signInRequest) {
        System.out.println("Logging in user with " + signInRequest.getEmail());
//        CreateUserRequest request = CreateUserRequest.newBuilder()
//                .setUsername("john_doe")
//                .setEmail("john@example.com")
//                .setPassword("secretpassword")
//                .build();
        //UserDAO signedUpUserDAO = this.userService.loginUser(signInRequest.getEmail());
        //return signedUpUserDAO.getEmail();
        return "hi";
    }
}
