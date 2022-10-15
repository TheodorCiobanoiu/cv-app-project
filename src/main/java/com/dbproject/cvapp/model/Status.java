package com.dbproject.cvapp.model;

//
public enum Status {
    Not_Reviewed,   // The recommendation has not been reviewed
    Reviewed,       // The recommendation has been reviewed but the scouting process has not ended
    In_Progress,    //The recomendation is in progress
    Accepted,       // Accepted after scouting process done
    Rejected        // Rejected after scouting process done
}
