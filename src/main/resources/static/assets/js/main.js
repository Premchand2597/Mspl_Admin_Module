/**
* Template Name: NiceAdmin
* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
* Updated: Apr 20 2024 with Bootstrap v5.3.3
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/

(function() {
  "use strict";

  /**
   * Easy selector helper function
   */
  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }

  /**
   * Easy event listener function
   */
  const on = (type, el, listener, all = false) => {
    if (all) {
      select(el, all).forEach(e => e.addEventListener(type, listener))
    } else {
      select(el, all).addEventListener(type, listener)
    }
  }

  /**
   * Easy on scroll event listener 
   */
  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  /**
   * Sidebar toggle
   */
  if (select('.toggle-sidebar-btn')) {
    on('click', '.toggle-sidebar-btn', function(e) {
      select('body').classList.toggle('toggle-sidebar')
    })
  }

  /**
   * Search bar toggle
   */
  if (select('.search-bar-toggle')) {
    on('click', '.search-bar-toggle', function(e) {
      select('.search-bar').classList.toggle('search-bar-show')
    })
  }

  /**
   * Navbar links active state on scroll
   */
  let navbarlinks = select('#navbar .scrollto', true)
  const navbarlinksActive = () => {
    let position = window.scrollY + 200
    navbarlinks.forEach(navbarlink => {
      if (!navbarlink.hash) return
      let section = select(navbarlink.hash)
      if (!section) return
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        navbarlink.classList.add('active')
      } else {
        navbarlink.classList.remove('active')
      }
    })
  }
  window.addEventListener('load', navbarlinksActive)
  onscroll(document, navbarlinksActive)

  /**
   * Toggle .header-scrolled class to #header when page is scrolled
   */
  let selectHeader = select('#header')
  if (selectHeader) {
    const headerScrolled = () => {
      if (window.scrollY > 100) {
        selectHeader.classList.add('header-scrolled')
      } else {
        selectHeader.classList.remove('header-scrolled')
      }
    }
    window.addEventListener('load', headerScrolled)
    onscroll(document, headerScrolled)
  }

  /**
   * Back to top button
   */
  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

  /**
   * Initiate tooltips
   */
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
  })

  /**
   * Initiate quill editors
   */
  if (select('.quill-editor-default')) {
    new Quill('.quill-editor-default', {
      theme: 'snow'
    });
  }

  if (select('.quill-editor-bubble')) {
    new Quill('.quill-editor-bubble', {
      theme: 'bubble'
    });
  }

  if (select('.quill-editor-full')) {
    new Quill(".quill-editor-full", {
      modules: {
        toolbar: [
          [{
            font: []
          }, {
            size: []
          }],
          ["bold", "italic", "underline", "strike"],
          [{
              color: []
            },
            {
              background: []
            }
          ],
          [{
              script: "super"
            },
            {
              script: "sub"
            }
          ],
          [{
              list: "ordered"
            },
            {
              list: "bullet"
            },
            {
              indent: "-1"
            },
            {
              indent: "+1"
            }
          ],
          ["direction", {
            align: []
          }],
          ["link", "image", "video"],
          ["clean"]
        ]
      },
      theme: "snow"
    });
  }

  /**
   * Initiate TinyMCE Editor
   */

  const useDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
  const isSmallScreen = window.matchMedia('(max-width: 1023.5px)').matches;

  tinymce.init({
    selector: 'textarea.tinymce-editor',
    plugins: 'preview importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media codesample table charmap pagebreak nonbreaking anchor insertdatetime advlist lists wordcount help charmap quickbars emoticons accordion',
    editimage_cors_hosts: ['picsum.photos'],
    menubar: 'file edit view insert format tools table help',
    toolbar: "undo redo | accordion accordionremove | blocks fontfamily fontsize | bold italic underline strikethrough | align numlist bullist | link image | table media | lineheight outdent indent| forecolor backcolor removeformat | charmap emoticons | code fullscreen preview | save print | pagebreak anchor codesample | ltr rtl",
    autosave_ask_before_unload: true,
    autosave_interval: '30s',
    autosave_prefix: '{path}{query}-{id}-',
    autosave_restore_when_empty: false,
    autosave_retention: '2m',
    image_advtab: true,
    link_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_class_list: [{
        title: 'None',
        value: ''
      },
      {
        title: 'Some class',
        value: 'class-name'
      }
    ],
    importcss_append: true,
    file_picker_callback: (callback, value, meta) => {
      /* Provide file and text for the link dialog */
      if (meta.filetype === 'file') {
        callback('https://www.google.com/logos/google.jpg', {
          text: 'My text'
        });
      }

      /* Provide image and alt text for the image dialog */
      if (meta.filetype === 'image') {
        callback('https://www.google.com/logos/google.jpg', {
          alt: 'My alt text'
        });
      }

      /* Provide alternative source and posted for the media dialog */
      if (meta.filetype === 'media') {
        callback('movie.mp4', {
          source2: 'alt.ogg',
          poster: 'https://www.google.com/logos/google.jpg'
        });
      }
    },
    height: 600,
    image_caption: true,
    quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
    noneditable_class: 'mceNonEditable',
    toolbar_mode: 'sliding',
    contextmenu: 'link image table',
    skin: useDarkMode ? 'oxide-dark' : 'oxide',
    content_css: useDarkMode ? 'dark' : 'default',
    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }'
  });

  /**
   * Initiate Bootstrap validation check
   */
  var needsValidation = document.querySelectorAll('.needs-validation')

  Array.prototype.slice.call(needsValidation)
    .forEach(function(form) {
      form.addEventListener('submit', function(event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })

  /**
   * Initiate Datatables
   */
  const datatables = select('.datatable', true)
  datatables.forEach(datatable => {
    new simpleDatatables.DataTable(datatable, {
      perPageSelect: [5, 10, 15, ["All", -1]],
      columns: [{
          select: 2,
          sortSequence: ["desc", "asc"]
        },
        {
          select: 3,
          sortSequence: ["desc"]
        },
        {
          select: 4,
          cellClass: "green",
          headerClass: "red"
        }
      ]
    });
  })

  /**
   * Autoresize echart charts
   */
  const mainContainer = select('#main');
  if (mainContainer) {
    setTimeout(() => {
      new ResizeObserver(function() {
        select('.echart', true).forEach(getEchart => {
          echarts.getInstanceByDom(getEchart).resize();
        })
      }).observe(mainContainer);
    }, 200);
  }

})();

//script to rotate humburger icon when user click on that 
 function toggleIconRotation() {
        const icon = document.querySelector('.toggle-sidebar-btn');
        icon.classList.toggle('rotated');
    }
    
    
/*document.getElementById("feedbackForm").addEventListener("submit", function (event) {
		    event.preventDefault(); // Prevent default form submission

		    $.ajax({
		        url: 'recentlySavedTicketNumber',
		        type: 'GET',
		        success: function (response) {
		            let recentlyInsertedTicketIdData = response;

		            // Get the current date
		            var today = new Date();
		            var year = today.getFullYear();
		            var month = today.getMonth() + 1; // JavaScript months are 0-based

		            // Calculate the financial year
		            var financialYearStart = month >= 4 ? year : year - 1; // Fiscal year starts in April
		            var financialYearEnd = financialYearStart + 1;

		            // Format as FYY-YY
		            var financialYear = `F${financialYearStart % 100}-${financialYearEnd % 100}`;

		            // Department Name
		            var loggedInDeptName = $('#loggedInDeptName').val();

		            let incrementedNumber = "0001"; // Default number if response is empty or null

		            // Check if recentlyInsertedTicketIdData is not empty or null
		            if (recentlyInsertedTicketIdData) {
		                // Extract the last part of the ticket (e.g., 0001)
		                let ticketParts = recentlyInsertedTicketIdData.split("/");
		                let lastPart = ticketParts[ticketParts.length - 1];

		                // Increment the extracted number
		                let numericValue = parseInt(lastPart, 10) + 1;
		                incrementedNumber = numericValue.toString().padStart(4, '0'); // Pad with leading zeros
		            }

		            // Construct the feedback ticket
		            var feedbackTicket = `MSPL/FT/${financialYear}/${loggedInDeptName}/${incrementedNumber}`;

		            // Add feedback ticket to the form data
		            let formData = new FormData(document.getElementById("feedbackForm"));
		            formData.append("id", feedbackTicket);
		            formData.append("emp_id", $('#emp_idSessionValue').val());

		            const submitButton = document.getElementById("submitFeedbackBtn");
		            submitButton.disabled = true; // Disable the submit button to prevent multiple submissions

		            const loadingSwal = Swal.fire({
		                title: 'Submitting feedback...',
		                text: 'Please wait while we submit your feedback.',
		                showConfirmButton: false,
		                allowOutsideClick: false, // Prevent closing when clicking outside
		                            allowEscapeKey: false, // Prevent closing when pressing escape key
		                didOpen: () => {
		                    Swal.showLoading(); // Display the loading spinner
		                }
		            });

		            // Append files to form data
		            let files = document.getElementById("feedbackAttachment").files;
		            if (files.length > 0) {
		                for (let i = 0; i < files.length; i++) {
		                    formData.append(`attachment[${i}]`, files[i]); // Use unique keys
		                }
		            }

		            let bugType = document.getElementById("bugType").value;
		            formData.append("bugTypeff", bugType); // Add the bug type to the form data

		            // Debugging: Log formData entries before the fetch call
		            console.log("Logging formData entries:");
		            for (let [key, value] of formData.entries()) {
		                if (value instanceof File) {
		                    console.log(`Key: ${key}, File Name: ${value.name}, Size: ${value.size}`);
		                } else {
		                    console.log(`Key: ${key}, Value: ${value}`);
		                }
		            }

					console.log("form data....."+JSON.stringify(formData));
					
		            // Make AJAX request to submit feedback
		            fetch("/submitFeedback", {
		                method: "POST",
		                body: formData
		            })
		                .then(response => response.json())
		                .then(data => {
							console.log("form data11....."+JSON.stringify(formData));
		                    loadingSwal.close();
		                    submitButton.disabled = false; // Re-enable the button
		                    if (data.success) {
		                        $('#feedbackModal').modal('hide');
		                        Swal.fire({
		                            title: 'Success!',
		                            text: 'Your feedback has been submitted successfully.',
		                            icon: 'success',
		                            allowOutsideClick: false, // Prevent closing when clicking outside
		                                        allowEscapeKey: false, // Prevent closing when pressing escape key
		                            customClass: {
		                                popup: 'custom-swal-font'
		                            },
		                            confirmButtonText: 'OK'
		                        }).then((result) => {
		                            if (result.isConfirmed) {
		                                    Swal.fire({
		                                            html: 'We sincerely appreciate your feedback! Please find your feedback ticket details below.<br><br>' + 
		                                    '<strong>' + feedbackTicket + '</strong>', // Display the feedback ticket on a new line
		                                    allowOutsideClick: false, // Prevent closing when clicking outside
		                                                allowEscapeKey: false, // Prevent closing when pressing escape key
		                                    customClass: {
		                                        popup: 'custom-swal-font'
		                                    },
		                                    confirmButtonText: 'OK'
		                                });
		                            }
		                        });
		                    } else {
		                        Swal.fire({
		                            title: 'Error!',
		                            text: 'There was an error submitting your feedback+++',
		                            icon: 'error',
		                            allowOutsideClick: false, // Prevent closing when clicking outside
		                                        allowEscapeKey: false, // Prevent closing when pressing escape key
		                            customClass: {
		                                popup: 'custom-swal-font'
		                            },
		                            confirmButtonText: 'OK'
		                        });
		                    }
		                })
		                .catch(error => {
		                    console.error('Error:', error);
		                    loadingSwal.close();
		                    submitButton.disabled = false; // Re-enable the button
		                    Swal.fire({
		                        title: 'Error!',
		                        text: 'There was an error submitting your feedback....',
		                        icon: 'error',
		                        allowOutsideClick: false, // Prevent closing when clicking outside
		                        allowEscapeKey: false, // Prevent closing when pressing escape key
		                        customClass: {
		                            popup: 'custom-swal-font'
		                        },
		                        confirmButtonText: 'OK'
		                    });
		                });
		        },
		        error: function (xhr, status, error) {
		            alert('Failed to fetch recently inserted feedback ticket: ' + error);
		        }
		    });
		});


		   // Clear form fields when the modal is closed
		   document.getElementById('feedbackModal').addEventListener('hidden.bs.modal', function () {
		       const feedbackForm = document.getElementById('feedbackForm');
		       feedbackForm.reset(); // Reset the form fields
		       document.getElementById('feedbackAttachment').value = ""; // Clear file input manually
		   });*/
		   
		   document.getElementById("feedbackForm").addEventListener("submit", function (event) {
		    event.preventDefault(); // Prevent default form submission

		    $.ajax({
		        url: 'recentlySavedTicketNumber',
		        type: 'GET',
		        success: function (response) {
		            let recentlyInsertedTicketIdData = response;

		            // Get the current date
		            var today = new Date();
		            var year = today.getFullYear();
		            var month = today.getMonth() + 1; // JavaScript months are 0-based

		            // Calculate the financial year
		            var financialYearStart = month >= 4 ? year : year - 1; // Fiscal year starts in April
		            var financialYearEnd = financialYearStart + 1;

		            // Format as FYY-YY
		            var financialYear = `F${financialYearStart % 100}-${financialYearEnd % 100}`;

		            // Department Name
		            var loggedInDeptName = $('#loggedInDeptName').val();

		            let incrementedNumber = "0001"; // Default number if response is empty or null

		            // Check if recentlyInsertedTicketIdData is not empty or null
		            if (recentlyInsertedTicketIdData) {
		                // Extract the last part of the ticket (e.g., 0001)
		                let ticketParts = recentlyInsertedTicketIdData.split("/");
		                let lastPart = ticketParts[ticketParts.length - 1];

		                // Increment the extracted number
		                let numericValue = parseInt(lastPart, 10) + 1;
		                incrementedNumber = numericValue.toString().padStart(4, '0'); // Pad with leading zeros
		            }

		            // Construct the feedback ticket
		            var feedbackTicket = `MSPL/FT/${financialYear}/${loggedInDeptName}/${incrementedNumber}`;

		            // Add feedback ticket to the form data
		            let formData = new FormData(document.getElementById("feedbackForm"));
		            formData.append("id", feedbackTicket);
		            formData.append("emp_id", $('#emp_idSessionValue').val());

		            const submitButton = document.getElementById("submitFeedbackBtn");
		            submitButton.disabled = true; // Disable the submit button to prevent multiple submissions

		            const loadingSwal = Swal.fire({
		                title: 'Submitting feedback...',
		                text: 'Please wait while we submit your feedback.',
		                showConfirmButton: false,
		                allowOutsideClick: false, // Prevent closing when clicking outside
		                            allowEscapeKey: false, // Prevent closing when pressing escape key
		                didOpen: () => {
		                    Swal.showLoading(); // Display the loading spinner
		                }
		            });

		            // Append files to form data
		            let files = document.getElementById("feedbackAttachment").files;
		            if (files.length > 0) {
		                for (let i = 0; i < files.length; i++) {
		                    formData.append(`attachment[${i}]`, files[i]); // Use unique keys
		                }
		            }

		            let bugType = document.getElementById("bugType").value;
		            formData.append("bugTypeff", bugType); // Add the bug type to the form data

		            // Debugging: Log formData entries before the fetch call
		            //console.log("Logging formData entries:");
		            for (let [key, value] of formData.entries()) {
		                if (value instanceof File) {
		                    //console.log(`Key: ${key}, File Name: ${value.name}, Size: ${value.size}`);
		                } else {
		                    //console.log(`Key: ${key}, Value: ${value}`);
		                }
		            }

					//console.log("form data....."+JSON.stringify(formData));
					
		            // Make AJAX request to submit feedback
		            fetch("/submitFeedback", {
		                method: "POST",
		                body: formData
		            })
		                .then(response => response.json())
		                .then(data => {
							//console.log("form data11....."+JSON.stringify(formData));
		                    loadingSwal.close();
		                    submitButton.disabled = false; // Re-enable the button
		                    if (data.success) {
		                        $('#feedbackModal').modal('hide');
		                        Swal.fire({
		                            title: 'Success!',
		                            text: 'Your feedback has been submitted successfully.',
		                            icon: 'success',
		                            allowOutsideClick: false, // Prevent closing when clicking outside
		                                        allowEscapeKey: false, // Prevent closing when pressing escape key
		                            customClass: {
		                                popup: 'custom-swal-font'
		                            },
		                            confirmButtonText: 'OK'
		                        }).then((result) => {
		                            if (result.isConfirmed) {
		                                    Swal.fire({
		                                            html: 'We sincerely appreciate your feedback! Please find your feedback ticket details below.<br><br>' + 
		                                    '<strong>' + feedbackTicket + '</strong>', // Display the feedback ticket on a new line
		                                    allowOutsideClick: false, // Prevent closing when clicking outside
		                                                allowEscapeKey: false, // Prevent closing when pressing escape key
		                                    customClass: {
		                                        popup: 'custom-swal-font'
		                                    },
		                                    confirmButtonText: 'OK'
		                                });
		                            }
		                        });
		                    } else {
		                        Swal.fire({
		                            title: 'Error!',
		                            text: 'There was an error submitting your feedback+++',
		                            icon: 'error',
		                            allowOutsideClick: false, // Prevent closing when clicking outside
		                                        allowEscapeKey: false, // Prevent closing when pressing escape key
		                            customClass: {
		                                popup: 'custom-swal-font'
		                            },
		                            confirmButtonText: 'OK'
		                        });
		                    }
		                })
		                .catch(error => {
		                    console.error('Error:', error);
		                    loadingSwal.close();
		                    submitButton.disabled = false; // Re-enable the button
		                    Swal.fire({
		                        title: 'Error!',
		                        text: 'There was an error submitting your feedback....',
		                        icon: 'error',
		                        allowOutsideClick: false, // Prevent closing when clicking outside
		                        allowEscapeKey: false, // Prevent closing when pressing escape key
		                        customClass: {
		                            popup: 'custom-swal-font'
		                        },
		                        confirmButtonText: 'OK'
		                    });
		                });
		        },
		        error: function (xhr, status, error) {
		            alert('Failed to fetch recently inserted feedback ticket: ' + error);
		        }
		    });
		});

		   // Clear form fields when the modal is closed
		   document.getElementById('feedbackModal').addEventListener('hidden.bs.modal', function () {
		       const feedbackForm = document.getElementById('feedbackForm');
		       feedbackForm.reset(); // Reset the form fields
		       document.getElementById('feedbackAttachment').value = ""; // Clear file input manually
		   });
	   
//script to notification 
// Function to fetch flag value from the backend
function fetchFlag() {
	//console.log("hiyyyyy");
    $.get("/flag", function(response) {
        var leaveRequestCountValue = response; 
        // console.log(leaveRequestCountValue);
        // If total notification count is greater than 0, show the bell icon and notifications
        if (leaveRequestCountValue.leaveRequestFlagCount > 0) {
            $("#notificationBellIcon").show();  // Show bell icon
            $("#leaveRequestNotification").show();  // Show specific notification
            
            $("#leaveRequestMessage").text(leaveRequestCountValue.leaveRequestFlagCount + " leave request(s)");
            $(".badge-number").text(leaveRequestCountValue.leaveRequestFlagCount);
            $("#leaveRequestFlagCount").text(leaveRequestCountValue.leaveRequestFlagCount);
        }  
        
        //display the asset notifu=ication based on asset count value
        if(leaveRequestCountValue.asseteRequestFlagCount>0){
        	 
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#assetRequestNotification").show();  // Show specific notification
             
             $("#assetRequestMessage").text(leaveRequestCountValue.asseteRequestFlagCount + " asset request(s)");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);
             $("#asseteRequestFlagCount").text(leaveRequestCountValue.asseteRequestFlagCount);
        	 	
        }      
        	
        //display the new project notification based on asset count value
        if(leaveRequestCountValue.newPrjectValueCount>0){
        	
        	 $("#notificationBellIcon").show();     // Show bell icon
             $("#newProjectNotification").show();  // Show specific notification
             
             $("#newProjectMessage").text(leaveRequestCountValue.newPrjectValueCount + " project request(s)");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);
             $("#newPrjectValueCount").text(leaveRequestCountValue.asseteRequestFlagCount); 
             
        }
        
        //display the appraisal from HR notification
        if(leaveRequestCountValue.appraisalCount>0){
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#hrAppraisal").show();  // Show specific notification
             
             $("#apprisalHrNotification").text(" Appraisal Form");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);
             $("#appraisalCount").text(leaveRequestCountValue.asseteRequestFlagCount);
        }
        
        //display the appraisal from employee notification 
        if(leaveRequestCountValue.empAppraisalflag>0){
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#empAppraisal").show();  // Show specific notification
             
             $("#apprisalempNotification").text(leaveRequestCountValue.empAppraisalflag + " employees sent appraisal");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);
             $("#empAppraisalflag").text(leaveRequestCountValue.asseteRequestFlagCount);
        }
        
        //display the appraisal Due date notification 
        if(leaveRequestCountValue.adminAppraisalDuedate>0){
        	 $("#dueAppraisal").show();  // Show bell icon
             $("#empAppraisal").show();  // Show specific notification
             
             $("#apprisalDueNotification").text("Tomorrow is the due date for submit appraisal");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);
             $("#adminAppraisalDuedate").text(leaveRequestCountValue.asseteRequestFlagCount);
        }
        
        //display the asset notifu=ication based on asset count value
        if(leaveRequestCountValue.announcementNotification>0){
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#newAnnouncementNotification").show();  // Show specific notification
             
             $("#newannouncement").text(leaveRequestCountValue.announcementNotification + " announcement(s)");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);             
        }  
        
        //display the leave request status change notification
        if(leaveRequestCountValue.leaveRequestStatusChangeValue > 0){
        	 
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#leaveRequestSatusChangeNotification").show();  // Show specific notification
             
             $("#leaveRequestSatusChangeMessage").text("Your leave request status has been");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);             
        	 	
        }  
        
        //display the to-do List event notification
        if(leaveRequestCountValue.toDoEvents > 0){
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#to-do-event").show();  // Show specific notification
             
             //$("#to-do-eventNotification").text("TO Do Reminder abcd");
             $("#to-do-eventNotification").html("TO Do Reminder <span style='display:block;'>Time is ticcking, there is 1 pending task(s from todo list, don't forget to complete your task before the deadline)</span>");

             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);             
        	 	
        } 
      
        //display the to-do List event notification
        if(leaveRequestCountValue.newFeatureNotification > 0){
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#new-feature").show();  // Show specific notification
             
             $("#new-featuretext").text("Latest Update, There is " + leaveRequestCountValue.newFeatureNotification + " new feature(s) update is now available.");
             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);             
        } 
      
        //display the new release
        if(leaveRequestCountValue.newReleaseNoteNotification > 0){
        	 $("#notificationBellIcon").show();  // Show bell icon
             $("#new-release").show();  // Show specific notification
             
             //$("#new-releasetext").text("Latest Update, There is " + leaveRequestCountValue.newFeatureNotification + " new feature(s) update is now available.");
			 // Option 3 – action-oriented
			 $("#new-releasetext").html("Version <strong>V.0.1.2</strong> is now available. Review the release notes for full details.");

             $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);             
        } 
		
		//display the Quotation
		if(leaveRequestCountValue.quotationNotification > 0){
			 $("#notificationBellIcon").show();  // Show bell icon
		     $("#quotation").show();  // Show specific notification
		     
		     //$("#new-releasetext").text("Latest Update, There is " + leaveRequestCountValue.newFeatureNotification + " new feature(s) update is now available.");
		     $("#quotationText").html("New Quotation is generated");

		     $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);             
		} 

		//display the Quotation
		if(leaveRequestCountValue.reviseQuotationCount > 0){
			 $("#notificationBellIcon").show();  // Show bell icon
		     $("#revisedQuotation").show();  // Show specific notification
		     
		     //$("#new-releasetext").text("Latest Update, There is " + leaveRequestCountValue.newFeatureNotification + " new feature(s) update is now available.");
		     $("#revisedQuotationText").html("Quotation is revised");
		     $(".badge-number").text(leaveRequestCountValue.totalNotificationCount);       
		} 
    }); 
} 

// Periodically fetch flag value every second (adjust as needed)
setInterval(fetchFlag, 1000);


//script to notification link redirect
    function redirectToLeaveRequest() {
        //var empid = /*[[${empDetailsByEmpId.empid}]]*/ 'defaultEmpId';
		var empid=document.getElementById('employeeId1').value;
		//alert(empid1);
        window.location.href = '/viewAdminLeaves?empid=' + empid;
    }
    function redirectToAsset() {
        //var empid = /*[[${empDetailsByEmpId.empid}]]*/ 'defaultEmpId';
        window.location.href = '/asset';
    }
    function redirectToProject() {
        //var empid = /*[[${empDetailsByEmpId.empid}]]*/ 'defaultEmpId';
        window.location.href = '/ongoingProject';
    }    
    function redirectToAppraisal() {
    	//var empid = /*[[${empDetailsByEmpId.empid}]]*/ 'defaultEmpId';
		var empid=document.getElementById('employeeId1').value;
    	window.location.href = '/apprisal?empid=' + empid;
    }    
    function redirectToemployeeAppraisal() {
    	//var empid = /*[[${empDetailsByEmpId.empid}]]*/ 'defaultEmpId';
		var empid=document.getElementById('employeeId1').value;
		window.location.href = '/employeeApprisal?empid=' + empid;
    }    
    function redirectToAnnouncement() {
    	//var empid = /*[[${empDetailsByEmpId.empid}]]*/ 'defaultEmpId';
		var empid=document.getElementById('employeeId1').value;
    	window.location.href = '/events';
    }
    function redirectToAttendence() {
    	//var email = /*[[${empDetailsByEmpId.email}]]*/ 'defaultEmpId';
		var email=document.getElementById('loggedEmpEmail').value;
    	var leaveTabActive='leaveTabActive';
    	//window.location.href = '/attendanceLive?email=' +email + '/'+ ;
    	 window.location.href = '/attendanceLive?email=' + email + '&tab=leaves' ;
    }
    function redirectToNewRelease() {
    	 window.location.href = '/releasenotes' ;
    }
    
    function redirectToNewFeature() {
    	 window.location.href = '/my-favorites' ;
    }
	function redirectTosales() {
	    window.location.href = '/sales#quotation';
	}
    
	// script to call unread message count 
	function fetchUnreadCount() {
	    //console.log("Fetching unread message count...");

	    var userEmailElement = document.getElementById('loggedInMail');
	    if (!userEmailElement) {
	        console.error("Error: #loggedInMail element not found!");
	        return;
	    }

	    var userEmail = userEmailElement.value;
	    //console.log("User email:", userEmail);

	    if (!userEmail) {
	        //console.warn("No email found, skipping fetch.");
	        return;
	    }

	    fetch(`/unread-count?email=${encodeURIComponent(userEmail)}`)
	        .then(response => {
	            //console.log("Response received:", response);
	            return response.json();
	        })
	        .then(count => {
	            //console.log("Unread count:", count);

	            const badge = document.getElementById("unreadCountBadge");		
				
	            if (!badge) {
	                //console.error("Error: #unreadCountBadge element not found!");
	                return;
	            }

	            if (count > 0) {
	                badge.textContent = count;
	                badge.style.display = "inline-block";
	            } else {
	                badge.style.display = "none";					
	            }
	        })
	        .catch(error => console.error("Error fetching unread messages:", error));
	}

	setInterval(fetchUnreadCount, 5000); // Fetch count every 5 seconds
	fetchUnreadCount(); // Initial fetch
	
	
	/*	// Detect internal navigation (links clicked within the same domain)
		let isNavigatingAway = false;

							// Detect internal navigation (links clicked within the same domain)
							document.addEventListener("click", function (event) {
							    let target = event.target.closest("a");
							    if (target && target.href.includes(window.location.origin)) {
							        isNavigatingAway = true;
							    }
							});

							// Handle back/forward navigation
							window.addEventListener("popstate", function () {
							    isNavigatingAway = true;
							});

							// Store session flag only when user reloads
							
							// Store session flag when the page loads
							sessionStorage.setItem("isReloading", "true");

							// Remove session flag on page load and check reload
							window.addEventListener("load", function () {
							    if (sessionStorage.getItem("isReloading") === "true") {
									
							       // alert("Page reloaded!"); // Debug alert for reload
							        console.log("Page reload detected");
									sendUserStatusUpdate();
							        sessionStorage.removeItem("isReloading"); // Remove flag after detecting reload
							    }
							});

							// Function to send status update on reload
							function sendUserStatusUpdate() {
							    const emailElement = document.getElementById("loggedInMail");
							    if (!emailElement) {
							        console.error("Email element not found.");
							        return;
							    }
								
							    const email = emailElement.value;
							    const formData = new FormData();
							    formData.append("email", email);

							    // Send API request on reload
							    navigator.sendBeacon("/updateUserStatusOnReload", formData);
							}
							// Detect page visibility changes
							document.addEventListener("visibilitychange", () => {
							    if (document.visibilityState === "visible") {
							        isNavigatingAway = false; // Reset if user is still on the site
							    }
							});

							
							// Handle tab/window close
							function handleTabClose(event) {
								
							    if (isNavigatingAway) {
							        console.log("Navigating within site, not sending API request.");
							        return; // Ignore if navigating within the site
							    }

							    const isReloading = sessionStorage.getItem("isReloading") === "true";
							    const emailElement = document.getElementById("loggedInMail");
								//console.log("Checking isReloading:", isReloading);
							    if (!emailElement) {
							        console.error("Email element not found.");
							        return;
							    }

							    const email = emailElement.value;
							    const formData = new FormData();
							    formData.append("email", email);

							    if (isReloading) {
							       // alert("Page is reloading, API request not sent."); // Alert for reload
							        //console.log("Reload detected, skipping API call.");
							        return; // Do not send API call on reload
							    }

							    // alert("Tab or window closed! Sending API request."); // Alert for tab close
							    //console.log("Sending API request to /updateUserStatusOnClose for:", email);
							    
							    // Use sendBeacon for reliability
							    navigator.sendBeacon("/updateUserStatusOnClose", formData);
							}

							// Use pagehide and beforeunload (for better reliability)
							window.addEventListener("pagehide", handleTabClose);
							window.addEventListener("beforeunload", handleTabClose);*/
								
//sript to display system notification
document.addEventListener("DOMContentLoaded", function () {
    if (Notification.permission === "default") {
        Notification.requestPermission();
    }
});

// Load previously notified message IDs from localStorage
let notifiedMessageIds = new Set(JSON.parse(localStorage.getItem("notifiedMessageIds")) || []);

async function checkForNotifications() {
    try {
        const email = document.getElementById('loggedInMail').value;
        const unreadResponse = await fetch(`/unread?email=${email}`);
        const unreadMessages = await unreadResponse.json();

        unreadMessages.forEach(msg => {
            if (!notifiedMessageIds.has(msg.id)) {
                showNotification("New Message", `From: ${msg.senderEmail} - ${msg.content}`, "/my-favorites", msg.senderEmail);
                notifiedMessageIds.add(msg.id);
            }
        });
        localStorage.setItem("notifiedMessageIds", JSON.stringify(Array.from(notifiedMessageIds)));
    } catch (error) {
        console.error("Error fetching unread notifications:", error.message);
    }
}

// Store previous notification counts separately
let previousNotificationCount = parseInt(localStorage.getItem("notificationCount")) || 0;
let previousLeaveNotificationCount = parseInt(localStorage.getItem("leaveNotificationCount")) || 0;
let previousLeaveStatusChangeCount = parseInt(localStorage.getItem("leaveStatusChangeCount")) || 0;
let previousAnnouncementNotification = parseInt(localStorage.getItem("announcementNotificationCount")) || 0;

async function checkNotificationCount() {
    const loggedAdminEmpId = document.getElementById("loggedAdminEmpId2")?.value || null;
    const loggedAdminEmailId2 = document.getElementById("loggedAdminEmailId2")?.value || null;

    try {
        const response = await fetch(`/flag`, { method: "GET", credentials: "include" });
        const data = await response.json();

        let newNotificationCount = data.totalNotificationCount;
        let newLeaveNotification = data.leaveRequestFlagCount;
        let newLeaveStatusChangeCount = data.leaveRequestStatusChangeValue;
        let newAnnouncementNotification = data.announcementNotification;

        if (newLeaveNotification > previousLeaveNotificationCount) {
            showNotification(
                "New Leave Request",
                `You have ${newLeaveNotification - previousLeaveNotificationCount} new leave requests.`,
                `/viewAdminLeaves?empid=${loggedAdminEmpId}`,
                data.senderEmail || ""
            );
        }

        if (newLeaveStatusChangeCount > previousLeaveStatusChangeCount) {
            showNotification(
                "Leave Status Update",
                `You have ${newLeaveStatusChangeCount - previousLeaveStatusChangeCount} leave status updates.`,
                `/attendanceLive?email=${loggedAdminEmailId2}`,
                data.senderEmail || ""
            );
        }

        if (newAnnouncementNotification > previousAnnouncementNotification) {
            showNotification(
                "New Announcement",
                `You have ${newAnnouncementNotification - previousAnnouncementNotification} new announcements.`,
                "/events",
                data.senderEmail || ""
            );
        }

        // Update stored notification counts
        previousNotificationCount = newNotificationCount;
        previousLeaveNotificationCount = newLeaveNotification;
        previousLeaveStatusChangeCount = newLeaveStatusChangeCount;
        previousAnnouncementNotification = newAnnouncementNotification;

        localStorage.setItem("notificationCount", String(newNotificationCount));
        localStorage.setItem("leaveNotificationCount", String(newLeaveNotification));
        localStorage.setItem("leaveStatusChangeCount", String(newLeaveStatusChangeCount));
        localStorage.setItem("announcementNotificationCount", String(newAnnouncementNotification));

    } catch (error) {
        console.error("Error fetching notification count:", error.message);
    }
}

// Function to show system notification with redirection
function showNotification(title, message, url, senderEmail) {
	
    if (Notification.permission === "granted") {
        const notification = new Notification(title, {
			 body: message,
			 icon: "assets/img/favicon.png" // Replace with the actual path to your logo
		  });

        notification.onclick = function () {
            sessionStorage.setItem("chatSenderEmail", senderEmail);
            window.focus();
            window.location.href = url;
        };
    } else if (Notification.permission === "default") {
        Notification.requestPermission().then(permission => {
            if (permission === "granted") {
                showNotification(title, message, url, senderEmail);
            }
        });
    }
}

// Check for notifications every 10 seconds
setInterval(() => {
    checkForNotifications();
    checkNotificationCount();
}, 1000); // Set to 10 seconds

// script to logout automaticcaly when user inactive upto 3hr
let inactivityTimeout;

function resetTimer() {
    clearTimeout(inactivityTimeout);
    inactivityTimeout = setTimeout(logoutUser, 10800000); 
}

function logoutUser() {
    window.location.href = "/"; // Change to your logout URL
}

// Reset timer on user activity
document.addEventListener("mousemove", resetTimer);
document.addEventListener("keypress", resetTimer);
document.addEventListener("click", resetTimer);
document.addEventListener("scroll", resetTimer);
document.addEventListener("touchstart", resetTimer);

// Initialize timer on page load
resetTimer();

