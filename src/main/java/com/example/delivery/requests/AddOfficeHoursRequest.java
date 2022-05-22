package com.example.delivery.requests;

import com.example.delivery.models.OfficeHours;

import java.util.List;

public record AddOfficeHoursRequest(List<OfficeHours> officeHours) {
}
