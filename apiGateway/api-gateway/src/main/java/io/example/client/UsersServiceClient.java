package io.example.client;

import io.example.grpc.users.UsersGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public final class UsersServiceClient {
    private ManagedChannel channel;
    private UsersGrpc.UsersBlockingStub blockingStub;

    public UsersServiceClient(){}

    public UsersServiceClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    public UsersServiceClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = UsersGrpc.newBlockingStub(channel);
    }

    public UsersGrpc.UsersBlockingStub getBlockingStub(){
        return blockingStub;
    }

    public ManagedChannel getChannel() {
        return channel;
    }

    public void shutdown(){
        channel.shutdownNow();
    }
}

