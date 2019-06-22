package io.example.client;

import io.example.grpc.emails.EmailsGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public final class EmailsServiceClient {
    private static ManagedChannel channel;
    private static EmailsGrpc.EmailsBlockingStub blockingStub;
    private static volatile EmailsServiceClient INSTANCE = null;

    private EmailsServiceClient(){
        this("localhost", 9999);
    }

    private EmailsServiceClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    private EmailsServiceClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = EmailsGrpc.newBlockingStub(channel);
    }

    public static synchronized EmailsServiceClient getInstance(){
        if (INSTANCE == null){
            INSTANCE = new EmailsServiceClient();
        }
        return INSTANCE;
    }

    public static synchronized EmailsServiceClient getInstance(String host, int port){
        if (INSTANCE == null){
            INSTANCE = new EmailsServiceClient(host, port);
        }
        return INSTANCE;
    }

    public EmailsGrpc.EmailsBlockingStub getBlockingStub(){
        return blockingStub;
    }

    public ManagedChannel getChannel() {
        return channel;
    }

    public void shutdown(){
        channel.shutdownNow();
    }
}
