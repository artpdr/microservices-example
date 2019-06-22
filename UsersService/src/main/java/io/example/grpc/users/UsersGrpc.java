package io.example.grpc.users;

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
    comments = "Source: users-service.proto")
public final class UsersGrpc {

  private UsersGrpc() {}

  public static final String SERVICE_NAME = "Users";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<User,
      ResponseStatus> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = User.class,
      responseType = ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<User,
      ResponseStatus> getCreateUserMethod() {
    io.grpc.MethodDescriptor<User, ResponseStatus> getCreateUserMethod;
    if ((getCreateUserMethod = UsersGrpc.getCreateUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getCreateUserMethod = UsersGrpc.getCreateUserMethod) == null) {
          UsersGrpc.getCreateUserMethod = getCreateUserMethod = 
              io.grpc.MethodDescriptor.<User, ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("CreateUser"))
                  .build();
          }
        }
     }
     return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Username,
      UserResponse> getReadUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReadUser",
      requestType = Username.class,
      responseType = UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Username,
      UserResponse> getReadUserMethod() {
    io.grpc.MethodDescriptor<Username, UserResponse> getReadUserMethod;
    if ((getReadUserMethod = UsersGrpc.getReadUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getReadUserMethod = UsersGrpc.getReadUserMethod) == null) {
          UsersGrpc.getReadUserMethod = getReadUserMethod = 
              io.grpc.MethodDescriptor.<Username, UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "ReadUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("ReadUser"))
                  .build();
          }
        }
     }
     return getReadUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<User,
      ResponseStatus> getUpdateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUser",
      requestType = User.class,
      responseType = ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<User,
      ResponseStatus> getUpdateUserMethod() {
    io.grpc.MethodDescriptor<User, ResponseStatus> getUpdateUserMethod;
    if ((getUpdateUserMethod = UsersGrpc.getUpdateUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getUpdateUserMethod = UsersGrpc.getUpdateUserMethod) == null) {
          UsersGrpc.getUpdateUserMethod = getUpdateUserMethod = 
              io.grpc.MethodDescriptor.<User, ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "UpdateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("UpdateUser"))
                  .build();
          }
        }
     }
     return getUpdateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Username,
      ResponseStatus> getDeleteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUser",
      requestType = Username.class,
      responseType = ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Username,
      ResponseStatus> getDeleteUserMethod() {
    io.grpc.MethodDescriptor<Username, ResponseStatus> getDeleteUserMethod;
    if ((getDeleteUserMethod = UsersGrpc.getDeleteUserMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getDeleteUserMethod = UsersGrpc.getDeleteUserMethod) == null) {
          UsersGrpc.getDeleteUserMethod = getDeleteUserMethod = 
              io.grpc.MethodDescriptor.<Username, ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "DeleteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("DeleteUser"))
                  .build();
          }
        }
     }
     return getDeleteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsernameAndToken,
      ResponseStatus> getConfirmUserEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmUserEmail",
      requestType = UsernameAndToken.class,
      responseType = ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsernameAndToken,
      ResponseStatus> getConfirmUserEmailMethod() {
    io.grpc.MethodDescriptor<UsernameAndToken, ResponseStatus> getConfirmUserEmailMethod;
    if ((getConfirmUserEmailMethod = UsersGrpc.getConfirmUserEmailMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getConfirmUserEmailMethod = UsersGrpc.getConfirmUserEmailMethod) == null) {
          UsersGrpc.getConfirmUserEmailMethod = getConfirmUserEmailMethod = 
              io.grpc.MethodDescriptor.<UsernameAndToken, ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "ConfirmUserEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsernameAndToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("ConfirmUserEmail"))
                  .build();
          }
        }
     }
     return getConfirmUserEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsernameAndPassword,
      TokenResponse> getAuthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Auth",
      requestType = UsernameAndPassword.class,
      responseType = TokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsernameAndPassword,
      TokenResponse> getAuthMethod() {
    io.grpc.MethodDescriptor<UsernameAndPassword, TokenResponse> getAuthMethod;
    if ((getAuthMethod = UsersGrpc.getAuthMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getAuthMethod = UsersGrpc.getAuthMethod) == null) {
          UsersGrpc.getAuthMethod = getAuthMethod = 
              io.grpc.MethodDescriptor.<UsernameAndPassword, TokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "Auth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsernameAndPassword.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TokenResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("Auth"))
                  .build();
          }
        }
     }
     return getAuthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Username,
      ResponseStatus> getCreateNewPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateNewPassword",
      requestType = Username.class,
      responseType = ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Username,
      ResponseStatus> getCreateNewPasswordMethod() {
    io.grpc.MethodDescriptor<Username, ResponseStatus> getCreateNewPasswordMethod;
    if ((getCreateNewPasswordMethod = UsersGrpc.getCreateNewPasswordMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getCreateNewPasswordMethod = UsersGrpc.getCreateNewPasswordMethod) == null) {
          UsersGrpc.getCreateNewPasswordMethod = getCreateNewPasswordMethod = 
              io.grpc.MethodDescriptor.<Username, ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "CreateNewPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("CreateNewPassword"))
                  .build();
          }
        }
     }
     return getCreateNewPasswordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UsernameTokenAndPassword,
      ResponseStatus> getUpdateUserPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUserPassword",
      requestType = UsernameTokenAndPassword.class,
      responseType = ResponseStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UsernameTokenAndPassword,
      ResponseStatus> getUpdateUserPasswordMethod() {
    io.grpc.MethodDescriptor<UsernameTokenAndPassword, ResponseStatus> getUpdateUserPasswordMethod;
    if ((getUpdateUserPasswordMethod = UsersGrpc.getUpdateUserPasswordMethod) == null) {
      synchronized (UsersGrpc.class) {
        if ((getUpdateUserPasswordMethod = UsersGrpc.getUpdateUserPasswordMethod) == null) {
          UsersGrpc.getUpdateUserPasswordMethod = getUpdateUserPasswordMethod = 
              io.grpc.MethodDescriptor.<UsernameTokenAndPassword, ResponseStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Users", "UpdateUserPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UsernameTokenAndPassword.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ResponseStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new UsersMethodDescriptorSupplier("UpdateUserPassword"))
                  .build();
          }
        }
     }
     return getUpdateUserPasswordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UsersStub newStub(io.grpc.Channel channel) {
    return new UsersStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UsersBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UsersBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UsersFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UsersFutureStub(channel);
  }

  /**
   */
  public static abstract class UsersImplBase implements io.grpc.BindableService {

    /**
     */
    public void createUser(User request,
                           io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     */
    public void readUser(Username request,
                         io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReadUserMethod(), responseObserver);
    }

    /**
     */
    public void updateUser(User request,
                           io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateUserMethod(), responseObserver);
    }

    /**
     */
    public void deleteUser(Username request,
                           io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteUserMethod(), responseObserver);
    }

    /**
     */
    public void confirmUserEmail(UsernameAndToken request,
                                 io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmUserEmailMethod(), responseObserver);
    }

    /**
     */
    public void auth(UsernameAndPassword request,
                     io.grpc.stub.StreamObserver<TokenResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuthMethod(), responseObserver);
    }

    /**
     */
    public void createNewPassword(Username request,
                                  io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateNewPasswordMethod(), responseObserver);
    }

    /**
     */
    public void updateUserPassword(UsernameTokenAndPassword request,
                                   io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateUserPasswordMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                User,
                ResponseStatus>(
                  this, METHODID_CREATE_USER)))
          .addMethod(
            getReadUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Username,
                UserResponse>(
                  this, METHODID_READ_USER)))
          .addMethod(
            getUpdateUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                User,
                ResponseStatus>(
                  this, METHODID_UPDATE_USER)))
          .addMethod(
            getDeleteUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Username,
                ResponseStatus>(
                  this, METHODID_DELETE_USER)))
          .addMethod(
            getConfirmUserEmailMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                UsernameAndToken,
                ResponseStatus>(
                  this, METHODID_CONFIRM_USER_EMAIL)))
          .addMethod(
            getAuthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                UsernameAndPassword,
                TokenResponse>(
                  this, METHODID_AUTH)))
          .addMethod(
            getCreateNewPasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Username,
                ResponseStatus>(
                  this, METHODID_CREATE_NEW_PASSWORD)))
          .addMethod(
            getUpdateUserPasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                UsernameTokenAndPassword,
                ResponseStatus>(
                  this, METHODID_UPDATE_USER_PASSWORD)))
          .build();
    }
  }

  /**
   */
  public static final class UsersStub extends io.grpc.stub.AbstractStub<UsersStub> {
    private UsersStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UsersStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsersStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UsersStub(channel, callOptions);
    }

    /**
     */
    public void createUser(User request,
                           io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readUser(Username request,
                         io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUser(User request,
                           io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteUser(Username request,
                           io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmUserEmail(UsernameAndToken request,
                                 io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmUserEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void auth(UsernameAndPassword request,
                     io.grpc.stub.StreamObserver<TokenResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createNewPassword(Username request,
                                  io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateNewPasswordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUserPassword(UsernameTokenAndPassword request,
                                   io.grpc.stub.StreamObserver<ResponseStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateUserPasswordMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UsersBlockingStub extends io.grpc.stub.AbstractStub<UsersBlockingStub> {
    private UsersBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UsersBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsersBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UsersBlockingStub(channel, callOptions);
    }

    /**
     */
    public ResponseStatus createUser(User request) {
      return blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public UserResponse readUser(Username request) {
      return blockingUnaryCall(
          getChannel(), getReadUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public ResponseStatus updateUser(User request) {
      return blockingUnaryCall(
          getChannel(), getUpdateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public ResponseStatus deleteUser(Username request) {
      return blockingUnaryCall(
          getChannel(), getDeleteUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public ResponseStatus confirmUserEmail(UsernameAndToken request) {
      return blockingUnaryCall(
          getChannel(), getConfirmUserEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public TokenResponse auth(UsernameAndPassword request) {
      return blockingUnaryCall(
          getChannel(), getAuthMethod(), getCallOptions(), request);
    }

    /**
     */
    public ResponseStatus createNewPassword(Username request) {
      return blockingUnaryCall(
          getChannel(), getCreateNewPasswordMethod(), getCallOptions(), request);
    }

    /**
     */
    public ResponseStatus updateUserPassword(UsernameTokenAndPassword request) {
      return blockingUnaryCall(
          getChannel(), getUpdateUserPasswordMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UsersFutureStub extends io.grpc.stub.AbstractStub<UsersFutureStub> {
    private UsersFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UsersFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UsersFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UsersFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseStatus> createUser(
        User request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<UserResponse> readUser(
        Username request) {
      return futureUnaryCall(
          getChannel().newCall(getReadUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseStatus> updateUser(
        User request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseStatus> deleteUser(
        Username request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseStatus> confirmUserEmail(
        UsernameAndToken request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmUserEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<TokenResponse> auth(
        UsernameAndPassword request) {
      return futureUnaryCall(
          getChannel().newCall(getAuthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseStatus> createNewPassword(
        Username request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateNewPasswordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseStatus> updateUserPassword(
        UsernameTokenAndPassword request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateUserPasswordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_READ_USER = 1;
  private static final int METHODID_UPDATE_USER = 2;
  private static final int METHODID_DELETE_USER = 3;
  private static final int METHODID_CONFIRM_USER_EMAIL = 4;
  private static final int METHODID_AUTH = 5;
  private static final int METHODID_CREATE_NEW_PASSWORD = 6;
  private static final int METHODID_UPDATE_USER_PASSWORD = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UsersImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UsersImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          serviceImpl.createUser((User) request,
              (io.grpc.stub.StreamObserver<ResponseStatus>) responseObserver);
          break;
        case METHODID_READ_USER:
          serviceImpl.readUser((Username) request,
              (io.grpc.stub.StreamObserver<UserResponse>) responseObserver);
          break;
        case METHODID_UPDATE_USER:
          serviceImpl.updateUser((User) request,
              (io.grpc.stub.StreamObserver<ResponseStatus>) responseObserver);
          break;
        case METHODID_DELETE_USER:
          serviceImpl.deleteUser((Username) request,
              (io.grpc.stub.StreamObserver<ResponseStatus>) responseObserver);
          break;
        case METHODID_CONFIRM_USER_EMAIL:
          serviceImpl.confirmUserEmail((UsernameAndToken) request,
              (io.grpc.stub.StreamObserver<ResponseStatus>) responseObserver);
          break;
        case METHODID_AUTH:
          serviceImpl.auth((UsernameAndPassword) request,
              (io.grpc.stub.StreamObserver<TokenResponse>) responseObserver);
          break;
        case METHODID_CREATE_NEW_PASSWORD:
          serviceImpl.createNewPassword((Username) request,
              (io.grpc.stub.StreamObserver<ResponseStatus>) responseObserver);
          break;
        case METHODID_UPDATE_USER_PASSWORD:
          serviceImpl.updateUserPassword((UsernameTokenAndPassword) request,
              (io.grpc.stub.StreamObserver<ResponseStatus>) responseObserver);
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

  private static abstract class UsersBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UsersBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.example.grpc.users.UsersService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Users");
    }
  }

  private static final class UsersFileDescriptorSupplier
      extends UsersBaseDescriptorSupplier {
    UsersFileDescriptorSupplier() {}
  }

  private static final class UsersMethodDescriptorSupplier
      extends UsersBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UsersMethodDescriptorSupplier(String methodName) {
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
      synchronized (UsersGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UsersFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .addMethod(getReadUserMethod())
              .addMethod(getUpdateUserMethod())
              .addMethod(getDeleteUserMethod())
              .addMethod(getConfirmUserEmailMethod())
              .addMethod(getAuthMethod())
              .addMethod(getCreateNewPasswordMethod())
              .addMethod(getUpdateUserPasswordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
