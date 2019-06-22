package io.example.config;

public final class EnvironmentVarsLoader {
    private final String jwtSecret;

    public EnvironmentVarsLoader() throws NoSuchFieldException {
        jwtSecret = System.getenv("JWT_SECRET");
        if(jwtSecret == null){
            throw new NoSuchFieldException("JWT_SECRET not set as environment variable");
        }
    }

    public String getJwtSecret() {
        return jwtSecret;
    }
}
