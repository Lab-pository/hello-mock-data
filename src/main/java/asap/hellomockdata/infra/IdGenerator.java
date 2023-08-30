package asap.hellomockdata.infra;

import com.github.f4b6a3.tsid.TsidFactory;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public final class IdGenerator {

    private static final int CONCURRENT_THREAD_THRESHOLD = 256;
    private static final Map<Long, TsidFactory> SNOWFLAKE_THREAD_FACTORY = new HashMap<>();

    static {
        for (int threadId = 0; threadId < CONCURRENT_THREAD_THRESHOLD; threadId++) {
            SNOWFLAKE_THREAD_FACTORY.put((long) threadId, TsidFactory.newInstance256(threadId));
        }
    }

    public long generate() {
        long currentThreadId = Thread.currentThread().getId();
        return SNOWFLAKE_THREAD_FACTORY.get(currentThreadId % CONCURRENT_THREAD_THRESHOLD)
            .create()
            .toLong();
    }
}

