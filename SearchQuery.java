public class SearchQuery {
    private List<SearchCondition> conditions;
    private String conditionalOperator;
    private List<OrderByCondition> orderByConditions;

    public SearchQuery() {
    }

    public SearchQuery(List<SearchCondition> conditions, String conditionalOperator, List<OrderByCondition> orderByConditions) {
        this.conditions = conditions;
        this.conditionalOperator = conditionalOperator;
        this.orderByConditions = orderByConditions;
    }

    // getters and setters
}

public class SearchCondition {
    private List<SearchCondition> conditions;
    private String conditionalOperator;
    private String columnName;
    private Object columnValue;
    private String operator;

    public SearchCondition() {
    }

    public SearchCondition(List<SearchCondition> conditions, String conditionalOperator, String columnName, Object columnValue, String operator) {
        this.conditions = conditions;
        this.conditionalOperator = conditionalOperator;
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.operator = operator;
    }

    // getters and setters
}

public class OrderByCondition {
    private String columnName;
    private String sortOrder;

    public OrderByCondition() {
    }

    public OrderByCondition(String columnName, String sortOrder) {
        this.columnName = columnName;
        this.sortOrder = sortOrder;
    }

    // getters and setters
}
