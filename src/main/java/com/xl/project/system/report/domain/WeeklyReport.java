package com.xl.project.system.report.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xl.framework.aspectj.lang.annotation.Excel;
import com.xl.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * list对象 weekly_report
 * 
 * @author xl
 * @date 2021-06-09
 */
public class WeeklyReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 解决状态 */
    @Excel(name = "解决状态")
    private String resolvedState;

    /** 需要协调方 */
    @Excel(name = "需要协调方")
    private String needCoordinator;

    /** 序号 */
    private Long no;

    /** 开始日期 */
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String startTime;

    /** 分类 */
    @Excel(name = "分类")
    private String classify;

    /** 现象 */
    @Excel(name = "现象")
    private String phenomenon;

    /** 提报人姓名 */
    @Excel(name = "提报人姓名")
    private String quesetionName;

    /** 所属职场 */
    @Excel(name = "所属职场")
    private String workplace;

    /** 故障原因 */
    @Excel(name = "故障原因")
    private String cause;

    /** 故障原因子项 */
    @Excel(name = "故障原因子项")
    private String failureCauseSubitem;

    /** 服务量 */
    @Excel(name = "服务量")
    private String volume;

    /** 处理措施 */
    @Excel(name = "处理措施")
    private String treatmentMeasures;

    /** 优化项 */
    @Excel(name = "优化项")
    private String optimizationItem;

    /** 业务是否中断 */
    @Excel(name = "业务是否中断")
    private String isBreak;

    /** 所属坐席 */
    @Excel(name = "所属坐席")
    private String seat;

    /** 接口人 */
    @Excel(name = "接口人")
    private String contactPerson;

    /** 结束日期 */
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopDate;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String stopTime;

    /** 关联问题号 */
    @Excel(name = "关联问题号")
    private Long relevanceNo;

    private String beginDate;

    private String endDate;

    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;
    }

    public String getBeginDate() {return beginDate;}

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getEndDate() {return endDate;}

    public void setResolvedState(String resolvedState) 
    {
        this.resolvedState = resolvedState;
    }

    public String getResolvedState() 
    {
        return resolvedState;
    }
    public void setNeedCoordinator(String needCoordinator) 
    {
        this.needCoordinator = needCoordinator;
    }

    public String getNeedCoordinator() 
    {
        return needCoordinator;
    }
    public void setNo(Long no) 
    {
        this.no = no;
    }

    public Long getNo() 
    {
        return no;
    }
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }
    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public String getStartTime() 
    {
        return startTime;
    }
    public void setClassify(String classify) 
    {
        this.classify = classify;
    }

    public String getClassify() 
    {
        return classify;
    }
    public void setPhenomenon(String phenomenon) 
    {
        this.phenomenon = phenomenon;
    }

    public String getPhenomenon() 
    {
        return phenomenon;
    }
    public void setQuesetionName(String quesetionName) 
    {
        this.quesetionName = quesetionName;
    }

    public String getQuesetionName() 
    {
        return quesetionName;
    }
    public void setWorkplace(String workplace) 
    {
        this.workplace = workplace;
    }

    public String getWorkplace() 
    {
        return workplace;
    }
    public void setCause(String cause) 
    {
        this.cause = cause;
    }

    public String getCause() 
    {
        return cause;
    }
    public void setFailureCauseSubitem(String failureCauseSubitem) 
    {
        this.failureCauseSubitem = failureCauseSubitem;
    }

    public String getFailureCauseSubitem() 
    {
        return failureCauseSubitem;
    }
    public void setVolume(String volume) 
    {
        this.volume = volume;
    }

    public String getVolume() 
    {
        return volume;
    }
    public void setTreatmentMeasures(String treatmentMeasures) 
    {
        this.treatmentMeasures = treatmentMeasures;
    }

    public String getTreatmentMeasures() 
    {
        return treatmentMeasures;
    }
    public void setOptimizationItem(String optimizationItem) 
    {
        this.optimizationItem = optimizationItem;
    }

    public String getOptimizationItem() 
    {
        return optimizationItem;
    }
    public void setIsBreak(String isBreak) 
    {
        this.isBreak = isBreak;
    }

    public String getIsBreak() 
    {
        return isBreak;
    }
    public void setSeat(String seat) 
    {
        this.seat = seat;
    }

    public String getSeat() 
    {
        return seat;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setStopDate(Date stopDate)
    {
        this.stopDate = stopDate;
    }

    public Date getStopDate()
    {
        return stopDate;
    }
    public void setStopTime(String stopTime) 
    {
        this.stopTime = stopTime;
    }

    public String getStopTime() 
    {
        return stopTime;
    }
    public void setRelevanceNo(Long relevanceNo) 
    {
        this.relevanceNo = relevanceNo;
    }

    public Long getRelevanceNo() 
    {
        return relevanceNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resolvedState", getResolvedState())
            .append("needCoordinator", getNeedCoordinator())
            .append("no", getNo())
            .append("startDate", getStartDate())
            .append("startTime", getStartTime())
            .append("classify", getClassify())
            .append("phenomenon", getPhenomenon())
            .append("quesetionName", getQuesetionName())
            .append("workplace", getWorkplace())
            .append("cause", getCause())
            .append("failureCauseSubitem", getFailureCauseSubitem())
            .append("volume", getVolume())
            .append("treatmentMeasures", getTreatmentMeasures())
            .append("optimizationItem", getOptimizationItem())
            .append("isBreak", getIsBreak())
            .append("seat", getSeat())
            .append("contactPerson", getContactPerson())
            .append("stopDate", getStopDate())
            .append("stopTime", getStopTime())
            .append("relevanceNo", getRelevanceNo())
            .toString();
    }
}
