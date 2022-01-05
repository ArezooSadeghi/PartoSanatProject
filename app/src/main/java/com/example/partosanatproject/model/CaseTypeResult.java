package com.example.partosanatproject.model;

public class CaseTypeResult extends ResultInfo {
    private CaseTypeInfo[] caseTypes;

    public CaseTypeInfo[] getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(CaseTypeInfo[] caseTypes) {
        this.caseTypes = caseTypes;
    }

    public class CaseTypeInfo {
        private int caseTypeID;
        private int parentID;
        private int userID;
        private long addTime;
        private boolean notRequiredCustomer;
        private String userFullName;
        private String caseType;

        public int getCaseTypeID() {
            return caseTypeID;
        }

        public void setCaseTypeID(int caseTypeID) {
            this.caseTypeID = caseTypeID;
        }

        public int getParentID() {
            return parentID;
        }

        public void setParentID(int parentID) {
            this.parentID = parentID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public boolean isNotRequiredCustomer() {
            return notRequiredCustomer;
        }

        public void setNotRequiredCustomer(boolean notRequiredCustomer) {
            this.notRequiredCustomer = notRequiredCustomer;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public String getCaseType() {
            return caseType;
        }

        public void setCaseType(String caseType) {
            this.caseType = caseType;
        }
    }
}
