package api;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserIdProvider {

    @DataProvider(name = "userIds")
    public static Object[][] provideUserIds() throws IOException {
        List<Object[]> userIdsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("userIds.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String userId = line.trim();
                userIdsList.add(new Object[]{userId});
            }
        }

        Object[][] userIdsArray = new Object[userIdsList.size()][];
        for (int i = 0; i < userIdsList.size(); i++) {
            userIdsArray[i] = userIdsList.get(i);
        }
        return userIdsArray;
    }
}
