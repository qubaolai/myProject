package com.qubaolai.po;

import java.util.ArrayList;
import java.util.List;

public class MoveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MoveExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberIsNull() {
            addCriterion("employee_number is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberIsNotNull() {
            addCriterion("employee_number is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberEqualTo(String value) {
            addCriterion("employee_number =", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberNotEqualTo(String value) {
            addCriterion("employee_number <>", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberGreaterThan(String value) {
            addCriterion("employee_number >", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberGreaterThanOrEqualTo(String value) {
            addCriterion("employee_number >=", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberLessThan(String value) {
            addCriterion("employee_number <", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberLessThanOrEqualTo(String value) {
            addCriterion("employee_number <=", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberLike(String value) {
            addCriterion("employee_number like", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberNotLike(String value) {
            addCriterion("employee_number not like", value, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberIn(List<String> values) {
            addCriterion("employee_number in", values, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberNotIn(List<String> values) {
            addCriterion("employee_number not in", values, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberBetween(String value1, String value2) {
            addCriterion("employee_number between", value1, value2, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumberNotBetween(String value1, String value2) {
            addCriterion("employee_number not between", value1, value2, "employeeNumber");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeIsNull() {
            addCriterion("move_before is null");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeIsNotNull() {
            addCriterion("move_before is not null");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeEqualTo(String value) {
            addCriterion("move_before =", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeNotEqualTo(String value) {
            addCriterion("move_before <>", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeGreaterThan(String value) {
            addCriterion("move_before >", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeGreaterThanOrEqualTo(String value) {
            addCriterion("move_before >=", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeLessThan(String value) {
            addCriterion("move_before <", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeLessThanOrEqualTo(String value) {
            addCriterion("move_before <=", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeLike(String value) {
            addCriterion("move_before like", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeNotLike(String value) {
            addCriterion("move_before not like", value, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeIn(List<String> values) {
            addCriterion("move_before in", values, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeNotIn(List<String> values) {
            addCriterion("move_before not in", values, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeBetween(String value1, String value2) {
            addCriterion("move_before between", value1, value2, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveBeforeNotBetween(String value1, String value2) {
            addCriterion("move_before not between", value1, value2, "moveBefore");
            return (Criteria) this;
        }

        public Criteria andMoveAfterIsNull() {
            addCriterion("move_after is null");
            return (Criteria) this;
        }

        public Criteria andMoveAfterIsNotNull() {
            addCriterion("move_after is not null");
            return (Criteria) this;
        }

        public Criteria andMoveAfterEqualTo(String value) {
            addCriterion("move_after =", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterNotEqualTo(String value) {
            addCriterion("move_after <>", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterGreaterThan(String value) {
            addCriterion("move_after >", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterGreaterThanOrEqualTo(String value) {
            addCriterion("move_after >=", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterLessThan(String value) {
            addCriterion("move_after <", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterLessThanOrEqualTo(String value) {
            addCriterion("move_after <=", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterLike(String value) {
            addCriterion("move_after like", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterNotLike(String value) {
            addCriterion("move_after not like", value, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterIn(List<String> values) {
            addCriterion("move_after in", values, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterNotIn(List<String> values) {
            addCriterion("move_after not in", values, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterBetween(String value1, String value2) {
            addCriterion("move_after between", value1, value2, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andMoveAfterNotBetween(String value1, String value2) {
            addCriterion("move_after not between", value1, value2, "moveAfter");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andMoveTypeIsNull() {
            addCriterion("move_type is null");
            return (Criteria) this;
        }

        public Criteria andMoveTypeIsNotNull() {
            addCriterion("move_type is not null");
            return (Criteria) this;
        }

        public Criteria andMoveTypeEqualTo(Integer value) {
            addCriterion("move_type =", value, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeNotEqualTo(Integer value) {
            addCriterion("move_type <>", value, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeGreaterThan(Integer value) {
            addCriterion("move_type >", value, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("move_type >=", value, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeLessThan(Integer value) {
            addCriterion("move_type <", value, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeLessThanOrEqualTo(Integer value) {
            addCriterion("move_type <=", value, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeIn(List<Integer> values) {
            addCriterion("move_type in", values, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeNotIn(List<Integer> values) {
            addCriterion("move_type not in", values, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeBetween(Integer value1, Integer value2) {
            addCriterion("move_type between", value1, value2, "moveType");
            return (Criteria) this;
        }

        public Criteria andMoveTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("move_type not between", value1, value2, "moveType");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(String value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(String value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(String value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(String value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(String value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(String value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLike(String value) {
            addCriterion("manager_id like", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotLike(String value) {
            addCriterion("manager_id not like", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<String> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<String> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(String value1, String value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(String value1, String value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andNotesIsNull() {
            addCriterion("notes is null");
            return (Criteria) this;
        }

        public Criteria andNotesIsNotNull() {
            addCriterion("notes is not null");
            return (Criteria) this;
        }

        public Criteria andNotesEqualTo(String value) {
            addCriterion("notes =", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotEqualTo(String value) {
            addCriterion("notes <>", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThan(String value) {
            addCriterion("notes >", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThanOrEqualTo(String value) {
            addCriterion("notes >=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThan(String value) {
            addCriterion("notes <", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThanOrEqualTo(String value) {
            addCriterion("notes <=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLike(String value) {
            addCriterion("notes like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotLike(String value) {
            addCriterion("notes not like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesIn(List<String> values) {
            addCriterion("notes in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotIn(List<String> values) {
            addCriterion("notes not in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesBetween(String value1, String value2) {
            addCriterion("notes between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotBetween(String value1, String value2) {
            addCriterion("notes not between", value1, value2, "notes");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}