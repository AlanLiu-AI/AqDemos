using System;
using AccessAqAcquisitionService.Aquarius.WebService.AAS;
using System.ServiceModel;
using System.ServiceModel.Channels;

namespace AccessAqAcquisitionService
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                var aqAquisitionServiceClient = new AQAcquisitionServiceClient("BasicHttpBinding_IAQAcquisitionService", "http://sooke/AQUARIUS/AqAcquisitionService.svc/Basic");
                //var aqAquisitionServiceClient = new AQAcquisitionServiceClient("WSHttpBinding_IAQAcquisitionService", "http://sooke/AQUARIUS/AqAcquisitionService.svc");
                var authToken = aqAquisitionServiceClient.GetAuthToken("admin", "admin");
                Console.WriteLine("Token: " + authToken);

                using (var contextScope = new OperationContextScope(aqAquisitionServiceClient.InnerChannel))
                {
                    var runtimeHeader = MessageHeader.CreateHeader("AQAuthToken", "", authToken, false);
                    OperationContext.Current.OutgoingMessageHeaders.Add(runtimeHeader);
                    var allLocations = aqAquisitionServiceClient.GetAllLocations();
                    Console.WriteLine("There are " + allLocations.Length + " Locations:");
                    foreach (var location in allLocations)
                    {
                        Console.WriteLine("\t" + location.Identifier);
                    }
                }
            }
            catch(Exception ex)
            {
                Console.WriteLine("Error occurred: " + ex.ToString());
            }
            Console.WriteLine("Enter to quit...");
            Console.ReadLine();
        }
    }
}
