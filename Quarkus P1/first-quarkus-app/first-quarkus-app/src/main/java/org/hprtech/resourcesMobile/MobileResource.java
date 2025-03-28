package org.hprtech.resourcesMobile;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hprtech.Model.Mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {

    List<Mobile> mobileList = new ArrayList<>();

    //get all mobile list
    @GET
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMobileList(){

        return Response.ok(mobileList).build();
    }
    //get , fecth single id list
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchMobileId(@PathParam("id") int id){

       Optional<Mobile> mobileId = mobileList.stream().filter(mobile -> mobile.getId()==id).findFirst();
      if (mobileId.isPresent()){
          return Response.ok(mobileId.get()).build();
      }else {
          return Response.ok(Response.Status.BAD_REQUEST).build();
      }
    }

    // save mobile data
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMobile(Mobile mobile){
        mobileList.add(mobile);
        return Response.ok(mobile).build();
    }

    //update mobiles
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id, Mobile mobileToUpdate){

        mobileList= mobileList.stream().map(mobile -> {
            if (mobile.getId()==id){
                return mobileToUpdate;
            }else {
                return mobile ;
            }
                   }).collect(Collectors.toList());

        return Response.ok(mobileList).build();

//        Mobile mobile = new Mobile();
//        if (mobileToUpdate.getId()==id){
//            mobile.setId(mobileToUpdate.getId());
//            mobile.setName(mobileToUpdate.getName());
//            mobile.setBrand(mobileToUpdate.getBrand());
//            mobile.setRam(mobileToUpdate.getRam());
//            mobile.setExternalStorage(mobileToUpdate.getExternalStorage());
//            mobileList.set(id,mobileToUpdate);
//            System.out.println("mobiles update");
//            return Response.ok(mobile).build();
//
//            }else {
//            Mobile newUpdate= new Mobile();
//            newUpdate.setId(mobileToUpdate.getId());
//            newUpdate.setName(mobileToUpdate.getName());
//            newUpdate.setBrand(mobileToUpdate.getBrand());
//            newUpdate.setRam(mobileToUpdate.getRam());
//            newUpdate.setExternalStorage(mobile.getExternalStorage());
//           // mobileList.add(mobileToUpdate);
//            System.out.println("mobileToUpdate");
//            return Response.ok(newUpdate).build();
//            }



    }

    //Delete id
    @DELETE
    @Path("/{id}")
   // @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id){
        //int index=mobileList.indexOf(id);
        //mobileList.remove(mobileList.get(id));
      Optional<Mobile> mobileToDelete = mobileList.stream()
              .filter(mobile -> mobile.getId()==id).findFirst();
        if(mobileToDelete.isPresent()){
            Mobile mobile = mobileToDelete.get();
            mobileList.remove(mobile);
         return Response.ok(mobileList).build();
        }else {
            return Response.ok(Response.Status.BAD_REQUEST).build();
        }
    }


    //quarkus-smallrye-openapi
    //./mvnw quarkus:add-extension -Dextensions="artifactId"

 }
