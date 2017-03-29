# TriangleCare
Group repository to track changes in TriangleCare app

Brittany  H update 3/29/2017:
I created a facilities page with the facility picture, information, map, calendar, and a link to go to announcements.
The layout xml file activity_facility.xml is the basic frame of the page and then it gets populated with data in FacilityActivity.
FacilityActivity is my main page and it reads in data from a file (facilities.txt) that it then applies to the page. Eventually
we can add the functionality to change the page based on which home a patient belongs to. For now, I just put info for Holy Cross and 
Wood Ridge, and you can see in the FacilityActivity script that it grabs the data for Holy Cross.
I created a Facility class to hold get/set functions for different strings and also a FileReader class to read in the data.
I also created a Maps activity that uses the Google Maps API and has a few pins for nursing homes. When you click the map screenshot,
it brings you to the map page. 
The next things I'll add are a link to a working calendar and fixing the toolbar across the bottom.
