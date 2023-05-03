package demo.SberTest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Контроллер выдачи и записи логов", description = "Записывает логи в БД и файлы, выдает логи в таблице")
@RequestMapping
@Controller
public class RecordController {

    @Autowired
    private RecordRepository repository;

    private final String dataDirectory = "src/main/resources/logs/log.txt";

    private Logger log = LoggerFactory.getLogger(RecordRepository.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${server.port}")
    private int serverPort;

    @Operation(summary = "выдача формы и логов", description = "выдача сохраненных логов из БД и формы для записи логов")
    @GetMapping("/logs")
    public String form(Model model) {
        if (repository.count() != 0) {
            List<Record> records = (List<Record>) repository.findAll();
            model.addAttribute("records", records);
        }

        return "records_template";
    }

    @Operation(summary = "сохранение лога", description = "Получает с фронта лог в виде JSON. Сохраняет лог в файл и БД (с помощью JPA).")
    @PostMapping("/logs")
    public String logs(
           /* @Parameter(description = "Лог, полученный в виде JSON объекта и превращенный Spring в объект класса Record")*/ @RequestBody Record request) {

        repository.save(request);

        try {
            File file = new File(dataDirectory, "a");
            PrintWriter pw = new PrintWriter(file);
            pw.println(mapper.writeValueAsString(request));
            pw.close();

        } catch (IOException e) {
            log.error("method={}, operation={}, exception={}, port={}", "updateFilenames", "addRecord",
                    e.getMessage(), serverPort);
        }

        return "redirect:/logs";

    }

}
