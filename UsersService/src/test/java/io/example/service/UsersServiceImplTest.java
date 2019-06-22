package io.example.service;

import io.example.business.UsersController;
import io.example.config.EnvironmentVarsLoader;
import io.example.grpc.users.*;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import io.grpc.testing.GrpcCleanupRule;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { UsersController.class })
public class UsersServiceImplTest {
    /**
     * This rule manages automatic graceful shutdown for the registered servers and channels at the
     * end of test.
     */
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Test
    public void testCreateUser() throws IOException, NoSuchFieldException {
        User testUser = createTestUser();
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.createUser(testUser)).thenReturn(
                ResponseStatus.newBuilder().setCode(201).setDetails("OK").build());


        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        ResponseStatus reply =
                blockingStub.createUser(testUser);

        Assert.assertEquals(reply.getDetails(), "OK");
        Assert.assertEquals(reply.getCode(), 201);

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.createUser(testUser);
    }

    @Test
    public void testReadUser() throws IOException, NoSuchFieldException {
        Username testUsername = createUsername("username_test");
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.readUser(testUsername)).thenReturn(
                UserResponse.newBuilder()
                        .setUser(
                                User.newBuilder()
                                        .setUsername(testUsername.getUsername())
                                        .build()

                        ).setResponseStatus(
                                ResponseStatus.newBuilder()
                                        .setCode(200)
                                        .setDetails("OK")
                                        .build())
                        .build());

        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        UserResponse reply =
                blockingStub.readUser(testUsername);

        Assert.assertEquals(reply.getResponseStatus().getDetails(), "OK");
        Assert.assertEquals(reply.getResponseStatus().getCode(), 200);
        Assert.assertEquals(reply.getUser().getUsername(), testUsername.getUsername());

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.readUser(testUsername);
    }

    @Test
    public void testUpdateUser() throws IOException, NoSuchFieldException {
        User testUser = createTestUser();
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.updateUser(testUser)).thenReturn(
                ResponseStatus.newBuilder().setCode(200).setDetails("OK").build());


        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        ResponseStatus reply =
                blockingStub.updateUser(testUser);

        Assert.assertEquals(reply.getDetails(), "OK");
        Assert.assertEquals(reply.getCode(), 200);

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.updateUser(testUser);
    }

    @Test
    public void testDeleteUser() throws IOException, NoSuchFieldException {
        Username testUsername = createUsername("username_test");
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.deleteUser(testUsername)).thenReturn(
                ResponseStatus.newBuilder().setCode(200).setDetails("OK").build());


        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        ResponseStatus reply =
                blockingStub.deleteUser(testUsername);

        Assert.assertEquals(reply.getDetails(), "OK");
        Assert.assertEquals(reply.getCode(), 200);

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.deleteUser(testUsername);
    }

    @Test
    public void testConfirmUserEmail() throws IOException, NoSuchFieldException {
        UsernameAndToken testUsernameAndToken = createUsernameAndToken("username_test", "xyz");
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.confirmUserEmail(testUsernameAndToken)).thenReturn(
                ResponseStatus.newBuilder().setCode(200).setDetails("OK").build());


        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        ResponseStatus reply =
                blockingStub.confirmUserEmail(testUsernameAndToken);

        Assert.assertEquals(reply.getDetails(), "OK");
        Assert.assertEquals(reply.getCode(), 200);

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.confirmUserEmail(testUsernameAndToken);
    }

    @Test
    public void testAuth() throws IOException, NoSuchFieldException {
        // Read environment variables
        EnvironmentVarsLoader envsLoader = new EnvironmentVarsLoader();
        UsernameAndPassword testUsernameAndPassword = createUsernameAndPassword("username_test", "xyz");
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.authenticateUser(testUsernameAndPassword, envsLoader.getJwtSecret(), 3600000)).thenReturn(
                TokenResponse.newBuilder()
                        .setResponseStatus(
                                ResponseStatus.newBuilder()
                                        .setCode(200)
                                        .setDetails("OK").build())
                        .setToken(
                                "xyz")
                        .build());

        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        TokenResponse reply =
                blockingStub.auth(testUsernameAndPassword);

        Assert.assertEquals(reply.getResponseStatus().getDetails(), "OK");
        Assert.assertEquals(reply.getResponseStatus().getCode(), 200);
        Assert.assertEquals(reply.getToken(), "xyz");

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.authenticateUser(testUsernameAndPassword, envsLoader.getJwtSecret(), 3600000);
    }

    @Test
    public void testCreateNewPassword() throws IOException, NoSuchFieldException {
        Username testUsername = createUsername("username_test");
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.createNewPassword(testUsername)).thenReturn(
                ResponseStatus.newBuilder().setCode(200).setDetails("OK").build());

        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        ResponseStatus reply =
                blockingStub.createNewPassword(testUsername);

        Assert.assertEquals(reply.getDetails(), "OK");
        Assert.assertEquals(reply.getCode(), 200);

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.createNewPassword(testUsername);
    }

    @Test
    public void testUpdateUserPassword() throws IOException, NoSuchFieldException {
        UsernameTokenAndPassword testUsernameTokenAndPassword = createUsernameTokenAndPassword("username_test", "abc", "xyz");
        PowerMockito.mockStatic(UsersController.class);
        Mockito.when(UsersController.updateUserPassword(testUsernameTokenAndPassword)).thenReturn(
                ResponseStatus.newBuilder().setCode(200).setDetails("OK").build());

        UsersGrpc.UsersBlockingStub blockingStub = createLocalUsersServiceBlockingStub();

        ResponseStatus reply =
                blockingStub.updateUserPassword(testUsernameTokenAndPassword);

        Assert.assertEquals(reply.getDetails(), "OK");
        Assert.assertEquals(reply.getCode(), 200);

        PowerMockito.verifyStatic(UsersController.class, Mockito.times(1));
        UsersController.updateUserPassword(testUsernameTokenAndPassword);
    }

    private UsersGrpc.UsersBlockingStub createLocalUsersServiceBlockingStub() throws IOException, NoSuchFieldException {
        // Read environment variables
        EnvironmentVarsLoader envsLoader = new EnvironmentVarsLoader();

        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(new UsersServiceImpl(envsLoader)).build().start());

        return UsersGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));
    }

    private User createTestUser() {
        return User.newBuilder()
                .setUsername("test")
                .setFirstName("first")
                .setLastName("last")
                .setEmail("first@last.com")
                .setPassword("qwerty")
                .build();
    }

    private Username createUsername(String username_test) {
        return Username.newBuilder()
                .setUsername(username_test)
                .build();
    }

    private UsernameAndToken createUsernameAndToken(String username_test, String token) {
        return UsernameAndToken.newBuilder()
                .setUsername(username_test)
                .setToken(token)
                .build();
    }

    private UsernameAndPassword createUsernameAndPassword(String username_test, String password) {
        return UsernameAndPassword.newBuilder()
                .setUsername(username_test)
                .setPassword(password)
                .build();
    }

    private UsernameTokenAndPassword createUsernameTokenAndPassword(String username_test, String token, String pass) {
        return UsernameTokenAndPassword.newBuilder()
                .setUsername(username_test)
                .setPassword(pass)
                .setToken(token)
                .build();
    }
}
