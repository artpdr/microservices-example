// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: emails-service.proto

package io.example.grpc.emails;

public final class EmailsService {
  private EmailsService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EmailConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EmailConfirmation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResponseStatus_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResponseStatus_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PasswordConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PasswordConfirmation_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\024emails-service.proto\"4\n\021EmailConfirmat" +
      "ion\022\020\n\010username\030\001 \001(\t\022\r\n\005token\030\002 \001(\t\"/\n\016" +
      "ResponseStatus\022\014\n\004code\030\001 \001(\r\022\017\n\007details\030" +
      "\002 \001(\t\"7\n\024PasswordConfirmation\022\020\n\010usernam" +
      "e\030\001 \001(\t\022\r\n\005token\030\002 \001(\t2|\n\006Emails\0225\n\014Conf" +
      "irmEmail\022\022.EmailConfirmation\032\017.ResponseS" +
      "tatus\"\000\022;\n\017ConfirmPassword\022\025.PasswordCon" +
      "firmation\032\017.ResponseStatus\"\000B\032\n\026io.examp" +
      "le.grpc.emailsP\001b\006proto3"
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
    internal_static_EmailConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EmailConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EmailConfirmation_descriptor,
        new String[] { "Username", "Token", });
    internal_static_ResponseStatus_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ResponseStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ResponseStatus_descriptor,
        new String[] { "Code", "Details", });
    internal_static_PasswordConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_PasswordConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PasswordConfirmation_descriptor,
        new String[] { "Username", "Token", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
