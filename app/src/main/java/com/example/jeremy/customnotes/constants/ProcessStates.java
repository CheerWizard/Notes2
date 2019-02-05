package com.example.jeremy.customnotes.constants;

public final class ProcessStates {
    //successful states
    public final static class Successful {
        public static final int DELETE_SUCCESS = 1;
        public static final int UPDATE_SUCCESS = 2;
        public static final int INSERT_SUCCESS = 3;
        public static final int DELETE_ALL_SUCCESS = 4;
    }
    //failed states
    public final static class Failed {
        public static final int DELETE_FAILED = -1;
        public static final int UPDATE_FAILED = -2;
        public static final int INSERT_FAILED = -3;
        public static final int DELETE_ALL_FAILED = -4;
    }
}
