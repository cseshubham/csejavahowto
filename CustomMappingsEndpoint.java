import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.web.mappings.MappingDescription;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CustomMappingsEndpoint {

    private final MappingsEndpoint mappingsEndpoint;

    public CustomMappingsEndpoint(MappingsEndpoint mappingsEndpoint) {
        this.mappingsEndpoint = mappingsEndpoint;
    }

    @ReadOperation
    @WebEndpoint
    @Endpoint(id = "mappings")
    public CustomMappingResponse mappings() {
        List<CustomMapping> customMappings = mappingsEndpoint.mappings().getMappings().stream()
                .map(CustomMapping::new)
                .collect(Collectors.toList());
        return new CustomMappingResponse(customMappings);
    }

    public static class CustomMappingResponse {

        private final List<CustomMapping> mappings;

        public CustomMappingResponse(List<CustomMapping> mappings) {
            this.mappings = mappings;
        }

        // Getters for 'mappings'
    }

    public static class CustomMapping {

        private final String method;
        private final String filter;

        public CustomMapping(MappingDescription mapping) {
            this.method = mapping.getMethods().toString();
            this.filter = mapping.getFilters().toString();
        }

        // Getters for 'method' and 'filter'
    }
}
