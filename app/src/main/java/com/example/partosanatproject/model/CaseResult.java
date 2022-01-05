package com.example.partosanatproject.model;

public class CaseResult extends ResultInfo {
    private CaseInfo[] cases;

    public CaseInfo[] getCases() {
        return cases;
    }

    public void setCases(CaseInfo[] cases) {
        this.cases = cases;
    }

    public class CaseInfo {
        private int caseID;
        private int caseTypeID;
        private int customerID;
        private int productionProjectID;
        private int contractID;
        private int checkInputPriceID;
        private int productionShippingReferenceID;
        private int productionOutReferenceID;
        private int priority;
        private int userID;
        private int closeUserID;
        private int deletedUserID;
        private int assignCount;
        private int attachCount;
        private int parentID;
        private long addTime;
        private long closeTime;
        private long deletedTime;
        private long lastUpdate;
        private boolean alarm;
        private boolean seenAssigner;
        private boolean resultOK;
        private String description;
        private String resultDescription;
        private AssignResult.AssignInfo[] Assigns;

        public int getCaseID() {
            return caseID;
        }

        public void setCaseID(int caseID) {
            this.caseID = caseID;
        }

        public int getCaseTypeID() {
            return caseTypeID;
        }

        public void setCaseTypeID(int caseTypeID) {
            this.caseTypeID = caseTypeID;
        }

        public int getCustomerID() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID = customerID;
        }

        public int getProductionProjectID() {
            return productionProjectID;
        }

        public void setProductionProjectID(int productionProjectID) {
            this.productionProjectID = productionProjectID;
        }

        public int getContractID() {
            return contractID;
        }

        public void setContractID(int contractID) {
            this.contractID = contractID;
        }

        public int getCheckInputPriceID() {
            return checkInputPriceID;
        }

        public void setCheckInputPriceID(int checkInputPriceID) {
            this.checkInputPriceID = checkInputPriceID;
        }

        public int getProductionShippingReferenceID() {
            return productionShippingReferenceID;
        }

        public void setProductionShippingReferenceID(int productionShippingReferenceID) {
            this.productionShippingReferenceID = productionShippingReferenceID;
        }

        public int getProductionOutReferenceID() {
            return productionOutReferenceID;
        }

        public void setProductionOutReferenceID(int productionOutReferenceID) {
            this.productionOutReferenceID = productionOutReferenceID;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getCloseUserID() {
            return closeUserID;
        }

        public void setCloseUserID(int closeUserID) {
            this.closeUserID = closeUserID;
        }

        public int getDeletedUserID() {
            return deletedUserID;
        }

        public void setDeletedUserID(int deletedUserID) {
            this.deletedUserID = deletedUserID;
        }

        public int getAssignCount() {
            return assignCount;
        }

        public void setAssignCount(int assignCount) {
            this.assignCount = assignCount;
        }

        public int getAttachCount() {
            return attachCount;
        }

        public void setAttachCount(int attachCount) {
            this.attachCount = attachCount;
        }

        public int getParentID() {
            return parentID;
        }

        public void setParentID(int parentID) {
            this.parentID = parentID;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public long getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(long closeTime) {
            this.closeTime = closeTime;
        }

        public long getDeletedTime() {
            return deletedTime;
        }

        public void setDeletedTime(long deletedTime) {
            this.deletedTime = deletedTime;
        }

        public long getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(long lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public boolean isAlarm() {
            return alarm;
        }

        public void setAlarm(boolean alarm) {
            this.alarm = alarm;
        }

        public boolean isSeenAssigner() {
            return seenAssigner;
        }

        public void setSeenAssigner(boolean seenAssigner) {
            this.seenAssigner = seenAssigner;
        }

        public boolean isResultOK() {
            return resultOK;
        }

        public void setResultOK(boolean resultOK) {
            this.resultOK = resultOK;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getResultDescription() {
            return resultDescription;
        }

        public void setResultDescription(String resultDescription) {
            this.resultDescription = resultDescription;
        }

        public AssignResult.AssignInfo[] getAssigns() {
            return Assigns;
        }

        public void setAssigns(AssignResult.AssignInfo[] assigns) {
            Assigns = assigns;
        }
    }
}
