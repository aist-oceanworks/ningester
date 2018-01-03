/*****************************************************************************
 * Copyright (c) 2017 Jet Propulsion Laboratory,
 * California Institute of Technology.  All rights reserved
 *****************************************************************************/

package gov.nasa.jpl.nexus.ningester.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("ningester")
@Component
public class ApplicationProperties {

    private List<String> tileProcessors = new ArrayList<>();

    @NestedConfigurationProperty
    private final AddDayOfYearAttribute addDayOfYearAttribute = new AddDayOfYearAttribute();

    @NestedConfigurationProperty
    private final AddTimeFromGranuleName addTimeFromGranuleName = new AddTimeFromGranuleName();

    @NestedConfigurationProperty
    private final AddTimeToSectionSpec addTimeToSectionSpec = new AddTimeToSectionSpec();

    @NestedConfigurationProperty
    private final GenerateTileId generateTileId = new GenerateTileId();

    @NestedConfigurationProperty
    private final PythonChainProcessor pythonChainProcessor = new PythonChainProcessor();

    @NestedConfigurationProperty
    private final AddDatasetName addDatasetName = new AddDatasetName();

    public PythonChainProcessor getPythonChainProcessor() {
        return pythonChainProcessor;
    }

    public List<String> getTileProcessors() {
        return tileProcessors;
    }

    public AddTimeToSectionSpec getAddTimeToSectionSpec() {
        return addTimeToSectionSpec;
    }

    public AddDayOfYearAttribute getAddDayOfYearAttribute() {
        return addDayOfYearAttribute;
    }

    public AddTimeFromGranuleName getAddTimeFromGranuleName() {
        return addTimeFromGranuleName;
    }

    public GenerateTileId getGenerateTileId() {
        return generateTileId;
    }

    public AddDatasetName getAddDatasetName() {
        return addDatasetName;
    }

}
