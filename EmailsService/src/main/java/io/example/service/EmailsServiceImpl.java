package io.example.service;

import io.example.business.EmailsController;
import io.example.grpc.emails.EmailConfirmation;
import io.example.grpc.emails.EmailsGrpc;
import io.example.grpc.emails.PasswordConfirmation;
import io.example.grpc.emails.ResponseStatus;
import io.grpc.stub.StreamObserver;

public final class EmailsServiceImpl extends EmailsGrpc.EmailsImplBase {
    @Override
    public void confirmEmail(EmailConfirmation request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(EmailsController.confirmEmail(request));
        responseObserver.onCompleted();
    }

    @Override
    public void confirmPassword(PasswordConfirmation request, StreamObserver<ResponseStatus> responseObserver) {
        responseObserver.onNext(EmailsController.confirmPassword(request));
        responseObserver.onCompleted();
    }
}
