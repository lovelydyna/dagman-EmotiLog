import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

public class LoggerConfig {
    private String logFilePath;
    private boolean consoleOutput;
    private String logLevel;
    
    public LoggerConfig(String configFilePath) {
        parseXML(configFilePath);
    }
    
    private void parseXML(String configFilePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(configFilePath));
            
            Element root = doc.getDocumentElement();
            this.logFilePath = root.getElementsByTagName("logFilePath").item(0).getTextContent();
            this.consoleOutput = Boolean.parseBoolean(root.getElementsByTagName("consoleOutput").item(0).getTextContent());
            this.logLevel = root.getElementsByTagName("logLevel").item(0).getTextContent();
        } catch (Exception e) {
            System.err.println("Error parsing XML config: " + e.getMessage());
        }
    }
    
    public String getLogFilePath() {
        return logFilePath;
    }
    
    public boolean isConsoleOutput() {
        return consoleOutput;
    }
    
    public String getLogLevel() {
        return logLevel;
    }
}
