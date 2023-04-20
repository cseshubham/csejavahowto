import java.util.List;

public class SearchQuery {
    private List<SearchCondition> conditions;
    private String operator;

    public List<SearchCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<SearchCondition> conditions) {
        this.conditions = conditions;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public static class SearchCondition {
        private List<SearchCondition> conditions;
        private String column_name;
        private Object column_value;
        private String operator;

        public List<SearchCondition> getConditions() {
            return conditions;
        }

        public void setConditions(List<SearchCondition> conditions) {
            this.conditions = conditions;
        }

        public String getColumn_name() {
            return column_name;
        }

        public void setColumn_name(String column_name) {
            this.column_name = column_name;
        }

        public Object getColumn_value() {
            return column_value;
        }

        public void setColumn_value(Object column_value) {
            this.column_value = column_value;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }
}
