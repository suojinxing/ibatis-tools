package org.workp.log.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@ConfigurationProperties(prefix = "mapperlog")
public class MapperLogProperties {
    private List<String> activeEnv;
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getActiveEnv() {
        return activeEnv;
    }

    public void setActiveEnv(List<String> activeEnv) {
        this.activeEnv = activeEnv;
    }
}
