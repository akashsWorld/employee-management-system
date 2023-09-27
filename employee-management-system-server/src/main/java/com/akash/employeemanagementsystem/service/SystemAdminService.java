package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.request_payloads.SystemAdminCreateRequest;
import com.akash.employeemanagementsystem.response_payload.SystemAdminPayload;

public interface SystemAdminService {

//    Change the system
    SystemAdminPayload addSystemAdmin(SystemAdminCreateRequest request);

    SystemAdminPayload changePassword(String email, String newPassword);
}
