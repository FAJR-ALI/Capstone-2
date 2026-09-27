Blood Donation System

- Project Description:
The Blood Donation System is a Spring Boot application designed to manage blood donors, blood requests, donations, donor health information, and blood inventory,
the system helps organize the blood donation process by matching donors with blood requests, checking donor eligibility, tracking donations, managing available blood units, 
and sending email notifications when a donation is accepted

Each Class has a Complete CRUD implementation using Model, Repository, Service, Controller Layers
- Classes:

Donor:
The Donor class represents people who can donate blood
 It contains :
-  donor's id,
-  name,
-  email,
-  blood type,
-  city
Extra Endpoints:
- getDonorByCity
- getDonorByBloodType
  
BloodRequest:
The BloodRequest class represents a request for blood
It contains:
- request id,
- blood type,
- quantity,
- city

Donation:
The Donation class represents a blood donation made by a donor for a specific blood request
It contains:
- donation id,
- donor,
- blood request
Extra Endpoints:
- getDonationCount: to count the number of Donations by a Donor
- getDonorBadge: its a badge based on the donation counts
the System also Send an Email notification to the donor email after the Donation is Successfully added
the Donation process also updates the blood inventory by increasing the avalible quantity of the Donor's blood Type 

VitalSigns:
The VitalSigns class stores the health information of a donor
It contains:
- id,
- donor,
- blood pressure,
- heart rate,
- temperature,
- hemoglobin level,
- blood sugar
Extra Endpoints:
- getVitalSignsByDonor: get all the vital Signs of a specific donor
- checkDonorEligibility: will check the vital Signs based of a specific values to confirm he's Eligibility 
- getEligibleDonors: will return a list of all the EligibleDonors

BloodInventory:
The BloodInventory class represents the available blood units in the system
It contains:
- id,
- blood type,
- quantity,
- city
Extra Endpoints:
- getInventoryByBloodType: check for the blood unit available by bloodType
- getInventoryByCity: check for the blood unit available by city
- getTotalQuantityByBloodType: check for all the blood unit quantity that have the same bloodType

Relationships Between Classes:
- The Donor class has a one-to-many relationship with Donation because one donor can make multiple donations
- The BloodRequest class has a one-to-many relationship with Donation because one blood request can receive multiple donations
- The Donation class has a many-to-one relationship with both Donor and BloodRequest
- The Donor class has a one-to-many relationship with VitalSigns because a donor can have multiple health checks





