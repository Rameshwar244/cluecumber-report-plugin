package com.trivago.cluecumber.rendering.pages.renderers;

import com.trivago.cluecumber.exceptions.CluecumberPluginException;
import com.trivago.cluecumber.rendering.charts.ChartJsonConverter;
import com.trivago.cluecumber.rendering.pages.pojos.pagecollections.PageCollection;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class PageRendererTest {

    private PageRenderer pageRenderer;

    @Before
    public void setup() {
        ChartJsonConverter chartJsonConverter = mock(ChartJsonConverter.class);
        pageRenderer = new PageRenderer(chartJsonConverter);
    }

    @Test
    public void processedContentTest() throws CluecumberPluginException {
        Template template = mock(Template.class);
        PageCollection pageCollection = mock(PageCollection.class);
        String processedContent = pageRenderer.processedContent(template, pageCollection);
        assertThat(processedContent, is(""));
    }

    @Test(expected = CluecumberPluginException.class)
    public void processedContentTemplateExceptionTest() throws Exception {
        Template template = mock(Template.class);
        doThrow(new TemplateException("Test", null)).when(template).process(any(PageCollection.class), any(Writer.class));
        PageCollection pageCollection = mock(PageCollection.class);
        String processedContent = pageRenderer.processedContent(template, pageCollection);
        assertThat(processedContent, is(""));
    }

    @Test(expected = CluecumberPluginException.class)
    public void processedContentIoExceptionTest() throws Exception {
        Template template = mock(Template.class);
        doThrow(new IOException("Test", null)).when(template).process(any(PageCollection.class), any(Writer.class));
        PageCollection pageCollection = mock(PageCollection.class);
        String processedContent = pageRenderer.processedContent(template, pageCollection);
        assertThat(processedContent, is(""));
    }
}
