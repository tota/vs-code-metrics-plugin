package org.jenkinsci.plugins.vs_code_metrics;

import hudson.model.AbstractBuild;

import org.jenkinsci.plugins.vs_code_metrics.bean.CodeMetrics;


public final class CodeMetricsReport extends AbstractReport {

    private final CodeMetrics result;

    /**
     *
     * @param build
     * @param result
     */
    public CodeMetricsReport(AbstractBuild<?,?> build, CodeMetrics result) {
        this.result = result;
        setBuild(build);
        setName("VsCodeMetrics");
    }

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public Object getReport(String token) {
        if ((result != null) && result.getChildren().containsKey(token))
            return new ModuleReport(getBuild(), result.getChildren().get(token));
        else
            return this;
    }

    @Override
    public boolean hasChildren() {
        return ((result != null) && (result.getChildren().size() > 0));
    }

    @Override
    public Object getChildren() {
        return result.getChildren();
    }

}