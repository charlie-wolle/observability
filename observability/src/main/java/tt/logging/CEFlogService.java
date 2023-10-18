package tt.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CEFlogService {

  // minimum severity value
  public static final int MIN_SEVERITY = 0;

  // maximum severity value
  public static final int MAX_SEVERITY = 10;

  // CEF version
  private static final long CEF_VERSION = 1L;

  @Value("${app.name}")
  private String deviceVendor;

  @Value("${app.description}")
  private String deviceProduct;

  @Value("${app.version}")
  private String deviceVersion;

  private static final Logger logger = LogManager.getLogger(CEFlogService.class);

  public void log(String deviceEventClassId, String name, String severity) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm.sss");
    Calendar c = Calendar.getInstance();
    String date = sdf.format(c.getTime());

    logger.info(
        "{} CEF:{}|{}|{}|{}|{}|{} - {}|{}",
        date,
        CEF_VERSION,
        deviceVendor,
        deviceProduct,
        deviceVersion,
        deviceEventClassId,
        ThreadContext.getContext(),
        name,
        severity);
  }
}
