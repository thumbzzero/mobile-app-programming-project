package com.example.mobileappproject.DBManager

object DBManager {
    private val DEBUGMODE = false

    /** get student info (by login id & pw)*/
    fun getSTDINFO(studentID: String, studentPW: String): DB_dc_std_info{
        for(it in DBInfoManager.dbDcStdInfoMap){
            if(it.value.id == studentID
                && it.value.pw == studentPW)
                return it.value
        }

        return DB_dc_std_info(name = "null")
    }

    /** get student info (by student key number)*/
    fun getSTDINFO(studentSID: String): DB_dc_std_info{
        for(it in DBInfoManager.dbDcStdInfoMap){
            if(it.key == studentSID)
                return it.value
        }

        return DB_dc_std_info(name = "null")
    }

    /** get student's course info (by student key number)*/
    fun getSTDCOS(studentSID: String): ArrayList<DB_dc_std_course_info>{
        var list = ArrayList<DB_dc_std_course_info>()
        for(it in DBInfoManager.dbDcStdCourseInfoMap){
            if(it.key.first == studentSID)
                list.add(it.value)
        }

        return list
    }

    /** get subject info (bu subject key number)*/
    fun getSUBINFO(subjectCODE: String): ArrayList<DB_dc_sub_info>{
        var list = ArrayList<DB_dc_sub_info>()
        for(it in DBInfoManager.dbDcSubInfoMap){
            if(it.key.first == subjectCODE)
                list.add(it.value)
        }

        return list
    }

    /** get subject homework info (by subject code and name)*/
    fun getSUBHW(subjectCODE: String, subjectNAME: String): ArrayList<DB_dc_sub_hw>{
        var list = ArrayList<DB_dc_sub_hw>()
        for(it in DBInfoManager.dbDcSubHwMap){
            if(it.key.first == subjectCODE
                && it.key.second == subjectNAME)
                list.add(it.value)
        }

        return list
    }

    /** get subject term test info (by subject code and name)*/
    fun getSUBTEST(subjectCODE: String, subjectNAME: String): ArrayList<DB_dc_sub_test>{
        var list = ArrayList<DB_dc_sub_test>()
        for(it in DBInfoManager.dbDcSubTestMap){
            if(it.key.first == subjectCODE
                && it.key.second == subjectNAME)
                list.add(it.value)
        }

        return list
    }

    /** get subject notification (by subject code and name)*/
    fun getSUBNOTI(subjectCODE: String, subjectNAME: String): ArrayList<DB_dc_sub_noti>{
        var list = ArrayList<DB_dc_sub_noti>()
        for(it in DBInfoManager.dbDcSubNotiMap){
            if(it.key.first == subjectCODE
                && it.key.second == subjectNAME)
                list.add(it.value)
        }

        return list
    }
}