package io.example.grpc.emails;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.0-SNAPSHOT)",
    comments = "Source: emails-service.proto")
public final class EmailsGrpc {

  private EmailsGrpc() {}

  public static final String SERVICE_NAME = "Emails";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<EmailConfirmation,
      io.example.grpc.emails.ResponseStatus> getConfirmEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmEmail",
      requestType = EmailConfirmation.class,
      responseType = io.example.grpc.emails.ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<EmailConfirmation,
      io.example.grpc.emails.ResponseStatus> getConfirmEmailMethod() {
    io.grpc.MethodDescriptor<EmailConfirmation, io.example.grpc.emails.ResponseStatus> getConfirmEmailMethod;
    if ((getConfirmEmailMethod = EmailsGrpc.getConfirmEmailMethod) == null) {
      synchronized (EmailsGrpc.class) {
        if ((getConfirmEmailMethod = EmailsGrpc.getConfirmEmailMethod) == null) {
          EmailsGrpc.getConfirmEmailMethod = getConfirmEmailMethod = 
              io.grpc.MethodDescriptor.<EmailConfirmation, io.example.grpc.emails.ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Emails", "ConfirmEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  EmailConfirmation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.example.grpc.emails.ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new EmailsMethodDescriptorSupplier("ConfirmEmail"))
                  .build();
          }
        }
     }
     return getConfirmEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.example.grpc.emails.PasswordConfirmation,
      io.example.grpc.emails.ResponseStatus> getConfirmPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmPassword",
      requestType = io.example.grpc.emails.PasswordConfirmation.class,
      responseType = io.example.grpc.emails.ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.example.grpc.emails.PasswordConfirmation,
      io.example.grpc.emails.ResponseStatus> getConfirmPasswordMethod() {
    io.grpc.MethodDescriptor<io.example.grpc.emails.PasswordConfirmation, io.example.grpc.emails.ResponseStatus> getConfirmPasswordMethod;
    if ((getConfirmPasswordMethod = EmailsGrpc.getConfirmPasswordMethod) == null) {
      synchronized (EmailsGrpc.class) {
        if ((getConfirmPasswordMethod = EmailsGrpc.getConfirmPasswordMethod) == null) {
          EmailsGrpc.getConfirmPasswordMethod = getConfirmPasswordMethod = 
              io.grpc.MethodDescriptor.<io.example.grpc.emails.PasswordConfirmation, io.example.grpc.emails.ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Emails", "ConfirmPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.example.grpc.emails.PasswordConfirmation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.example.grpc.emails.ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new EmailsMethodDescriptorSupplier("ConfirmPassword"))
                  .build();
          }
        }
     }
     return getConfirmPasswordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmailsStub newStub(io.grpc.Channel channel) {
    return new EmailsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmailsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EmailsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmailsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EmailsFutureStub(channel);
  }

  /**
   */
  public static abstract class EmailsImplBase implements io.grpc.BindableService {

    /**
     */
    public void confirmEmail(EmailConfirmation request,
                             io.grpc.stub.StreamObserver<io.example.grpc.emails.ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmEmailMethod(), responseObserver);
    }

    /**
     */
    public void confirmPassword(io.example.grpc.emails.PasswordConfirmation request,
        io.grpc.stub.StreamObserver<io.example.grpc.emails.ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmPasswordMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConfirmEmailMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                EmailConfirmation,
                io.example.grpc.emails.ResponseStatus>(
                  this, METHODID_CONFIRM_EMAIL)))
          .addMethod(
            getConfirmPasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.example.grpc.emails.PasswordConfirmation,
                io.example.grpc.emails.ResponseStatus>(
                  this, METHODID_CONFIRM_PASSWORD)))
          .build();
    }
  }

  /**
   */
  public static final class EmailsStub extends io.grpc.stub.AbstractStub<EmailsStub> {
    private EmailsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmailsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmailsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmailsStub(channel, callOptions);
    }

    /**
     */
    public void confirmEmail(EmailConfirmation request,
                             io.grpc.stub.StreamObserver<io.example.grpc.emails.ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmPassword(io.example.grpc.emails.PasswordConfirmation request,
        io.grpc.stub.StreamObserver<io.example.grpc.emails.ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmPasswordMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EmailsBlockingStub extends io.grpc.stub.AbstractStub<EmailsBlockingStub> {
    private EmailsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmailsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmailsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmailsBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.example.grpc.emails.ResponseStatus confirmEmail(EmailConfirmation request) {
      return blockingUnaryCall(
          getChannel(), getConfirmEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.example.grpc.emails.ResponseStatus confirmPassword(io.example.grpc.emails.PasswordConfirmation request) {
      return blockingUnaryCall(
          getChannel(), getConfirmPasswordMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EmailsFutureStub extends io.grpc.stub.AbstractStub<EmailsFutureStub> {
    private EmailsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmailsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmailsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmailsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.example.grpc.emails.ResponseStatus> confirmEmail(
        EmailConfirmation request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.example.grpc.emails.ResponseStatus> confirmPassword(
        io.example.grpc.emails.PasswordConfirmation request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmPasswordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CONFIRM_EMAIL = 0;
  private static final int METHODID_CONFIRM_PASSWORD = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EmailsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EmailsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONFIRM_EMAIL:
          serviceImpl.confirmEmail((EmailConfirmation) request,
              (io.grpc.stub.StreamObserver<io.example.grpc.emails.ResponseStatus>) responseObserver);
          break;
        case METHODID_CONFIRM_PASSWORD:
          serviceImpl.confirmPassword((io.example.grpc.emails.PasswordConfirmation) request,
              (io.grpc.stub.StreamObserver<io.example.grpc.emails.ResponseStatus>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EmailsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmailsBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.example.grpc.emails.EmailsService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Emails");
    }
  }

  private static final class EmailsFileDescriptorSupplier
      extends EmailsBaseDescriptorSupplier {
    EmailsFileDescriptorSupplier() {}
  }

  private static final class EmailsMethodDescriptorSupplier
      extends EmailsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EmailsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EmailsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmailsFileDescriptorSupplier())
              .addMethod(getConfirmEmailMethod())
              .addMethod(getConfirmPasswordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
