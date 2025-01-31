package cz.todo_project.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.stereotype.Service;

@Service
public class DefaultMsgService implements MsgService {
    @Override
    public String getMsg() {
        return String.format("Hi there!! it's %s here..",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }
}