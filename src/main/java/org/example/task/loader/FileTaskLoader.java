package org.example.task.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.task.model.Task;
import org.example.user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTaskLoader implements TaskLoader {

    private static final String FILENAME_PATTERN = "%s_%s_tasks.json";

    private static final List<Task> EMPTY_STATE = new ArrayList<>();

    private final ObjectMapper objectMapper;

    public FileTaskLoader() {
        objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<Task> load(User<?> user) {
        String filename = String.format(FILENAME_PATTERN, user.getId(), user.getUsername());
        File file = new File(filename);
        if (!file.exists()) {
            return EMPTY_STATE;
        }
        TypeReference<ArrayList<Task>> typeRef = new TypeReference<>() {
        };
        try {
            return this.objectMapper.readValue(file, typeRef);
        } catch (IOException ex) {
            throw new LoadException("unable to load tasks", ex);
        }
    }

    @Override
    public void save(User<?> user, List<Task> tasks) {
        String filename = String.format(FILENAME_PATTERN, user.getId(), user.getUsername());
        try {
            this.objectMapper.writeValue(new File(filename), tasks);
        } catch (IOException ex) {
            throw new SaveException("unable to save tasks", ex);
        }
    }
}
