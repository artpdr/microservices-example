// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: users-service.proto

package io.example.grpc.users;

public final class UsersService {
  private UsersService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_User_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_User_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResponseStatus_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResponseStatus_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Username_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Username_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UsernameAndToken_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UsernameAndToken_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UsernameAndPassword_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UsernameAndPassword_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TokenResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TokenResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UsernameTokenAndPassword_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UsernameTokenAndPassword_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\023users-service.proto\"`\n\004User\022\020\n\010usernam" +
      "e\030\001 \001(\t\022\022\n\nfirst_name\030\002 \001(\t\022\021\n\tlast_name" +
      "\030\003 \001(\t\022\r\n\005email\030\004 \001(\t\022\020\n\010password\030\005 \001(\t\"" +
      "/\n\016ResponseStatus\022\014\n\004code\030\001 \001(\r\022\017\n\007detai" +
      "ls\030\002 \001(\t\"M\n\014UserResponse\022\023\n\004user\030\001 \001(\0132\005" +
      ".User\022(\n\017response_status\030\002 \001(\0132\017.Respons" +
      "eStatus\"\034\n\010Username\022\020\n\010username\030\001 \001(\t\"3\n" +
      "\020UsernameAndToken\022\020\n\010username\030\001 \001(\t\022\r\n\005t" +
      "oken\030\002 \001(\t\"9\n\023UsernameAndPassword\022\020\n\010use" +
      "rname\030\001 \001(\t\022\020\n\010password\030\002 \001(\t\"H\n\rTokenRe" +
      "sponse\022\r\n\005token\030\001 \001(\t\022(\n\017response_status" +
      "\030\002 \001(\0132\017.ResponseStatus\"M\n\030UsernameToken" +
      "AndPassword\022\020\n\010username\030\001 \001(\t\022\r\n\005token\030\002" +
      " \001(\t\022\020\n\010password\030\003 \001(\t2\214\003\n\005Users\022&\n\nCrea" +
      "teUser\022\005.User\032\017.ResponseStatus\"\000\022&\n\010Read" +
      "User\022\t.Username\032\r.UserResponse\"\000\022&\n\nUpda" +
      "teUser\022\005.User\032\017.ResponseStatus\"\000\022*\n\nDele" +
      "teUser\022\t.Username\032\017.ResponseStatus\"\000\0228\n\020" +
      "ConfirmUserEmail\022\021.UsernameAndToken\032\017.Re" +
      "sponseStatus\"\000\022.\n\004Auth\022\024.UsernameAndPass" +
      "word\032\016.TokenResponse\"\000\0221\n\021CreateNewPassw" +
      "ord\022\t.Username\032\017.ResponseStatus\"\000\022B\n\022Upd" +
      "ateUserPassword\022\031.UsernameTokenAndPasswo" +
      "rd\032\017.ResponseStatus\"\000B\031\n\025io.example.grpc" +
      ".usersP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_User_descriptor,
        new String[] { "Username", "FirstName", "LastName", "Email", "Password", });
    internal_static_ResponseStatus_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ResponseStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ResponseStatus_descriptor,
        new String[] { "Code", "Details", });
    internal_static_UserResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_UserResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserResponse_descriptor,
        new String[] { "User", "ResponseStatus", });
    internal_static_Username_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Username_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Username_descriptor,
        new String[] { "Username", });
    internal_static_UsernameAndToken_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_UsernameAndToken_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UsernameAndToken_descriptor,
        new String[] { "Username", "Token", });
    internal_static_UsernameAndPassword_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_UsernameAndPassword_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UsernameAndPassword_descriptor,
        new String[] { "Username", "Password", });
    internal_static_TokenResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_TokenResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TokenResponse_descriptor,
        new String[] { "Token", "ResponseStatus", });
    internal_static_UsernameTokenAndPassword_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_UsernameTokenAndPassword_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UsernameTokenAndPassword_descriptor,
        new String[] { "Username", "Token", "Password", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}