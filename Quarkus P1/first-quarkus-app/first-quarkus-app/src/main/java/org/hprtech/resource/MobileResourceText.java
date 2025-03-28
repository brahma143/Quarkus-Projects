package org.hprtech.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mob") //localhost:8080/mobile
public class MobileResourceText {

    List<String> mobileList = new ArrayList<>();

    //
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public List<String> getMobileList(){
//        return mobileList;
//    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllMobiles(){
        return Response.ok(mobileList).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addNewMobile(String mobileName){
        mobileList.add(mobileName);
       return Response.ok(mobileName).build();
       // mobileList.add(mobileName);
    }

    //update exsting name
//    @PUT
//    @Path("{oldName}")
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response updateMobileList(@PathParam("{oldName}") String oldName, String newName){
//      int index = mobileList.indexOf(oldName);
//      if (index == -1){
//          return Response.status(Response.Status.NOT_FOUND)
//                  .entity("Mobile Name "+oldName+" not Found new Mobile Name" ).build();
//      }
//       mobileList.set(index,newName);
//       return Response.ok("Updated '" + oldName + "' to '" + newName + "'").build();
//    }

    //http://localhost:8080/mobile/1  mobile?newMobileName=1Plus mobile
                                  //oldMobileName?newMobileName=value
    @PUT
    @Path("/{oldMobileName}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("oldMobileName") String oldMobileName,
                                 @QueryParam("newMobileName") String newMobileName){
        mobileList = mobileList.stream().map(mob->{
            if (mob.equals(oldMobileName)) {

                return newMobileName;
            }else {
                return mob;
            }
        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();

    }

    //delete mobile name
     @DELETE
     @Path("/{mobileToDelete}")
     //@Consumes(MediaType.TEXT_PLAIN)
     @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileName){
       boolean isRemoved=mobileList.remove(mobileName);
       if(isRemoved){
           return Response.ok("Successfully mobile deleted "+mobileName).build();

       }else {
           return Response.status(Response.Status.BAD_REQUEST).build();
       }
    }

    /*
    *
    *
    * */
    @DELETE
    @Path("/all")
    //@Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAll(){
        mobileList.removeAll(mobileList);

        return Response.ok("Deleted All").build();
    }
}
