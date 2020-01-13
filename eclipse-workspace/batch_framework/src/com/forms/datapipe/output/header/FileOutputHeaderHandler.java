package com.forms.datapipe.output.header;

import com.forms.datapipe.Output;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;

public interface FileOutputHeaderHandler
{
    public String handle(Output output, PipeContext pipeContext)
        throws DataPipeException;
}
