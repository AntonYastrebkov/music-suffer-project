import React from 'react';

import { AdminServiceConsumer } from '../context'

const withAdminService = () => (Wrapped) => {
  return (props) => {
    return (
      <AdminServiceConsumer>
        {
          (adminService) => {
            return (<Wrapped {...props}
                adminService={adminService} />);
          }
        }
      </AdminServiceConsumer>
    );
  }
};

export default withAdminService;