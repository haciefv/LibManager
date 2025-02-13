package domain.core.model.enums;

public enum Role {

    ADMIN,
    USER;

    public static String[] names() {
        Role[] roles = Role.values();
        String[] names = new String[roles.length];
        for (int i = 0; i < roles.length; i++) {
            names[i] = roles[i].name();
        }
        return names;
    }
}
