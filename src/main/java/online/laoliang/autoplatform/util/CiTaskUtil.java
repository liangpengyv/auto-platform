package online.laoliang.autoplatform.util;

import online.laoliang.autoplatform.properties.CurrentProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CiTaskUtil {

    final static String SUCCESS_STATUS = "SUCCESS";
    final static String FAILED_STATUS = "FAILED";

    public static String executePythonScript(CurrentProperties currentProperties, String stepName, String[] pythonParameters) {

        String pythonScript = System.getProperty("user.dir") + "\\script\\" + stepName + ".py";

        List<String> executeStatement = new ArrayList<>();
        executeStatement.add("python");
        executeStatement.add(pythonScript);
        if (pythonParameters != null) {
            for (String pythonParameter : pythonParameters) {
                executeStatement.add(pythonParameter);
            }
        }

        Process process;
        StringBuilder logBuilder = new StringBuilder();
        String stepStatus = "UNKNOWN";
        int pythonExitValue = -1;
        long startTime = 0;
        long endTime = 0;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(executeStatement);
            // ProcessBuilder processBuilder = new ProcessBuilder("python", "./script/test.py");
            processBuilder.redirectErrorStream(true);

            // 获取开始时间
            startTime = System.currentTimeMillis();
            // 开始执行脚本
            process = processBuilder.start();
            // 等待脚本执行结束并返回
            process.waitFor();
            // 获取结束时间
            endTime = System.currentTimeMillis();

            // 读取脚本输出日志
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), currentProperties.getEncode()));
            String line;
            while ((line = input.readLine()) != null) {
                logBuilder.append("\n").append(line);
            }
            input.close();

            if (process.exitValue() == 0) {
                // Python 环境正常，脚本正常启动
                // 继续读取 Python 脚本退出码，判断脚本执行情况
                pythonExitValue = Integer.parseInt(logBuilder.substring(logBuilder.lastIndexOf("\n") + 1, logBuilder.length()));
                if (pythonExitValue == 0) {
                    // Python 脚本退出码为 0，脚本执行成功（即任务成功）
                    stepStatus = SUCCESS_STATUS;
                } else {
                    // Python 脚本退出码不为 0，脚本执行失败
                    stepStatus = FAILED_STATUS;
                }
            } else {
                stepStatus = FAILED_STATUS;
                // 为最后格式化日志输出匹配规则，这里在末尾追加一个"\n"
                logBuilder.append("\n");
            }

        } catch (IOException e) {
            logBuilder.append("Cannot run program python: CreateProcess error=2, 系统找不到指定的文件。\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return logBuilder.toString().substring(0, logBuilder.lastIndexOf("\n")) + "\n" +
                "[CI] ------------------------------------------------------------------------\n" +
                "[CI] " + stepName.toUpperCase() + " " + stepStatus + "\n" +
                "[CI] ------------------------------------------------------------------------\n" +
                "[CI] Total time: " + ((float) (endTime - startTime) / 1000) + " s\n" +
                "[CI] Finished at: " + new Date(endTime) + "\n" +
                "[CI] ------------------------------------------------------------------------\n" +
                "[CI] Exit: " + pythonExitValue; // 最后的 Python 脚本退出码用于前端判断任务执行状态
    }
}
