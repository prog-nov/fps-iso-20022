package com.forms.datapipe.config;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

public class PipeConfigRuleSet
    extends RuleSetBase
{

    @Override
    public void addRuleInstances(Digester digester)
    {
        setRoot(digester);
        setPipeData(digester);
        addGlobalForward(digester);
        addInput(digester);
        addOutput(digester);
        addValve(digester);
        addValveForward(digester);
        setMultiThreadConfig(digester);
    }

    private void setRoot(Digester digester)
    {
        String node = "pipe-config";
        digester.addObjectCreate(node, PipeConfig.class);
        digester.addBeanPropertySetter(node + "/performance/stop-at-err", "stopAtErr");
    }

    private void setPipeData(Digester digester)
    {
        String node = "pipe-config/pipe-data";
        digester.addObjectCreate(node, PipeDataConfig.class);
        addPipeDataField(digester);
        digester.addSetNext(node, "setPipeData");
    }

    private void addPipeDataField(Digester digester)
    {
        String node = "pipe-config/pipe-data/field";
        digester.addObjectCreate(node, PipeField.class);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "data-type", "dataType");
        digester.addSetNext(node, "addPipeField", PipeField.class.getName());
    }

    private void addGlobalForward(Digester digester)
    {
        String node = "pipe-config/global-forwards/forward";
        digester.addObjectCreate(node, ValveForward.class);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "next-valve", "nextValve");
        digester.addSetNext(node, "addGlobalForward",
            ValveForward.class.getName());
    }

    private void addInput(Digester digester)
    {
        String node = "pipe-config/inputs/input";
        digester.addObjectCreate(node, InputConfig.class);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "class", "clazz");
        digester.addSetProperties(node, "to-valve", "toValve");
        digester.addBeanPropertySetter(node + "/config-file", "configFile");
        digester.addSetNext(node, "addInput", InputConfig.class.getName());
    }

    private void addOutput(Digester digester)
    {
        String node = "pipe-config/outputs/output";
        digester.addObjectCreate(node, OutputConfig.class);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "class", "clazz");
        digester.addSetProperties(node, "from-valve", "fromValve");
        digester.addBeanPropertySetter(node + "/config-file", "configFile");
        digester.addSetNext(node, "addOutput", OutputConfig.class.getName());
    }

    private void addValve(Digester digester)
    {
        String node = "pipe-config/valves/valve";
        digester.addObjectCreate(node, ValveConfig.class);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "class", "clazz");
        digester.addSetProperties(node, "next-valve", "nextValve");
        digester.addBeanPropertySetter(node + "/config-file", "configFile");
        digester.addSetNext(node, "addValve", ValveConfig.class.getName());
    }

    private void addValveForward(Digester digester)
    {
        String node = "pipe-config/valves/valve/forwards/forward";
        digester.addObjectCreate(node, ValveForward.class);
        digester.addSetProperties(node, "name", "name");
        digester.addSetProperties(node, "next-valve", "nextValve");
        digester.addSetNext(node, "addValveForward",
            ValveForward.class.getName());
    }
    
    private void setMultiThreadConfig(Digester digester)
    {
        String node = "pipe-config/performance/multi-thread";
        digester.addObjectCreate(node, MultiThreadConfig.class);
        digester.addSetProperties(node, "use", "use");
        digester.addSetProperties(node, "keep-order", "keepOrder");
        digester.addSetProperties(node, "buffer-size", "bufferSize");
        digester.addSetNext(node, "setMultiThreadConfig", MultiThreadConfig.class.getName());
    }
}
