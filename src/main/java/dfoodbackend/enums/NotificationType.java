package dfoodbackend.enums;

public enum NotificationType {
    SMS("Sms notification channel"),
    EMAIL("Email notification channel"),
    PUSH("Push notification channel");

    NotificationType(String description) {
    }
}
