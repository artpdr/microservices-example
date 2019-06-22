package io.example.service;

import io.example.business.UsersController;
import io.example.config.EnvironmentVarsLoader;
import io.example.grpc.users.*;
import io.grpc.stub.StreamObserver;

public class UsersServiceImpl extends UsersGrpc.UsersImplBase {
    private final EnvironmentVarsLoader envsLoader;

    public UsersServiceImpl(EnvironmentVarsLoader envsLoader){
        this.envsLoader = envsLoader;
    }

    @Override
    public void createUser(User request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(UsersController.createUser(request));
        responseObserver.onCompleted();
    }

    @Override
    public void readUser(Username request, StreamObserver<UserResponse> responseObserver) {
        responseObserver.onNext(UsersController.readUser(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(User request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(UsersController.updateUser(request));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(Username request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(UsersController.deleteUser(request));
        responseObserver.onCompleted();
    }

    @Override
    public void confirmUserEmail(UsernameAndToken request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(UsersController.confirmUserEmail(request));
        responseObserver.onCompleted();
    }

    @Override
    public void auth(UsernameAndPassword request, StreamObserver<TokenResponse> responseObserver) {
        responseObserver.onNext(
                UsersController.authenticateUser(request, envsLoader.getJwtSecret(), 24*60*60*1000));
        responseObserver.onCompleted();
    }

    @Override
    public void createNewPassword(Username request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(UsersController.createNewPassword(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateUserPassword(UsernameTokenAndPassword request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(UsersController.updateUserPassword(request));
        responseObserver.onCompleted();
    }
}
