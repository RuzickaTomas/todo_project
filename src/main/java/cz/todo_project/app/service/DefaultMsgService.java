package cz.todo_project.app.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import cz.todo_project.app.service.MsgService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class DefaultMsgService implements MsgService {
    @Override
    public String getMsg() {
        return String.format("Hi there!! it's %s here..",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }
}