// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: emails-service.proto

package io.example.grpc.emails;

public interface PasswordConfirmationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PasswordConfirmation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string username = 1;</code>
   */
  String getUsername();
  /**
   * <code>string username = 1;</code>
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string token = 2;</code>
   */
  String getToken();
  /**
   * <code>string token = 2;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}