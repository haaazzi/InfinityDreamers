package com.nhnacademy.node;

import com.nhnacademy.WireType;
import com.nhnacademy.trash.SystemResources;

public class ResourceNode extends InputOutputNode{
    
    public void process(){
        if ((getInputWire(WireType.RESOURCE) != null) && getInputWire(WireType.RESOURCE).hasMessage()) {
            Message message = getInputWire(WireType.RESOURCE).get();
            SystemResources sysresources = new SystemResources();
            
            String contents = sysresources.getResources();

            Response response = message.getResponse();
            response.setContents(contents);
            output(message, WireType.PARSER);
        }
    }
}
