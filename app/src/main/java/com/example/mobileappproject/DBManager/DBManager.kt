package com.example.mobileappproject.DBManager

object DBManager {
    private val DEBUGMODE = true

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

    /** get subject info (bu subject code)*/
    fun getSUBINFO(subjectCODE: String): ArrayList<DB_dc_sub_info>{
        var list = ArrayList<DB_dc_sub_info>()
        for(it in DBInfoManager.dbDcSubInfoMap){
            if(it.key.first == subjectCODE)
                list.add(it.value)
        }

        return list
    }

    /** get subject homework info (by subject code)*/
    fun getSUBHWTEST(subjectCODE: String): ArrayList<DB_dc_sub_hwtest>{
        for(it in DBInfoManager.dbDcSubHwTestMap){
            if(it.key == subjectCODE)
                return it.value
        }

        return arrayListOf()
    }

    /** get subject notification (by subject code)*/
    fun getSUBNOTI(subjectCODE: String): ArrayList<DB_dc_sub_noti>{
        for(it in DBInfoManager.dbDcSubNotiMap){
            if(it.key == subjectCODE)
                return it.value
        }

        return arrayListOf()
    }

    /** get subject lecture material (by subject code)*/
    fun getSUBLECMAT(subjectCODE: String): ArrayList<DB_dc_sub_lecmat>{
        for(it in DBInfoManager.dbDcSubLecmatMap){
            if(it.key == subjectCODE)
                return it.value
        }

        return arrayListOf()
    }

    fun getSTDMESSAGE(recieveStudentSID: String): ArrayList<DB_dc_std_message>{
        for(it in DBInfoManager.dbDcStdMessageMap){
            if(it.key == recieveStudentSID)
                return it.value
        }

        return arrayListOf()
    }

    fun sendSTDMESSAGE(sendStudentSID: String, recieveStudentSID: String,
                       messageContent: String, messageModifyDate: String){

        DBInfoManager.set_STDMessage_Table(
            sendStudentSID, recieveStudentSID,
            messageContent, messageModifyDate)

    }

}