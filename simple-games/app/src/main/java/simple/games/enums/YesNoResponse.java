package simple.games.enums;

import java.util.Arrays;
import java.util.Optional;

public enum YesNoResponse {
    YES("yes"),
    NO("no");

    private String value;

    YesNoResponse(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static Optional<YesNoResponse> fromString(String value) {
        return Arrays.stream(YesNoResponse.values())
                .filter(response -> response.value.equalsIgnoreCase(value))
                .findFirst();
    }
}
